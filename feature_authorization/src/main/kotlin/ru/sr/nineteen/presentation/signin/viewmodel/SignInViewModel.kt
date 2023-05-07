package ru.sr.nineteen.presentation.signin.viewmodel

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.Dispatchers
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.data.mapper.AuthUiMapper
import ru.sr.nineteen.data.repository.FirebaseNoEmailVerifications
import ru.sr.nineteen.data.repository.FirebaseNotAuth
import ru.sr.nineteen.domain.Validation
import ru.sr.nineteen.domain.usecase.PutTokenUseCase
import ru.sr.nineteen.domain.usecase.SignInWithEmailUseCase
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInAction
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInEvent
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInState

class SignInViewModel(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val uiMapper: AuthUiMapper,
    private val validation: Validation,
    private val putTokenUseCase: PutTokenUseCase,
) : BaseViewModel<SignInState, SignInAction, SignInEvent>(SignInState()) {

    override fun obtainEvent(viewEvent: SignInEvent) {
        when (viewEvent) {
            is SignInEvent.OnClickRegistrationButton -> openRegistrationScreen(viewState.email)
            SignInEvent.OnClickSignInButton ->
                onClickSignIn(viewState.email, viewState.password)
            SignInEvent.OnClickSkipAuthButton -> openMenu()
            is SignInEvent.OnChangeEmail -> onChangeEmail(viewEvent.email)
            is SignInEvent.OnChangePassword -> onChangePassword(viewEvent.password)
            SignInEvent.OnClearEmail -> onClearEmail()
            SignInEvent.OnClickForgotPasswordButton -> onOpenResetPasswordScreen()
            SignInEvent.OnOpenWarning -> openWarningMessage()
            SignInEvent.OnResetAction -> onResetAction()
        }
    }

    private fun onClickSignIn(email: String, password: String) {
        viewState = viewState.copy(
            isErrorEmailValidation = !validation.emailValidation(viewState.email),
            isErrorPasswordValidation = !validation.passwordValidation(viewState.password)
        )

        if (!viewState.isErrorEmailValidation && !viewState.isErrorPasswordValidation)
            startAuth(email, password)
    }

    private fun openWarningMessage() {
        viewAction = SignInAction.OpenWarningMessage
    }

    private fun onOpenResetPasswordScreen() {
        viewAction = SignInAction.OpenResetPassword(viewState.email)
    }

    private fun onClearEmail() {
        viewState = viewState.copy(email = "", isErrorEmailValidation = false)
    }

    private fun onChangeEmail(email: String) {
        viewState = viewState.copy(email = email, isErrorEmailValidation = false)
    }

    private fun onChangePassword(password: String) {
        viewState = viewState.copy(password = password, isErrorPasswordValidation = false)
    }

    private fun openRegistrationScreen(email: String) {
        viewAction = SignInAction.OpenRegistration(email)
    }

    private fun startAuth(email: String, password: String) {
        scopeLaunch(
            context = Dispatchers.IO,
            onLoading = ::startLoadingAuth,
            onError = ::onError
        ) {
            val user = uiMapper
                .authUserDomainModelToAuthUser(signInWithEmailUseCase.signIn(email, password))
            putTokenUseCase.putToken(user.token)
            openMenu(user.email)
        }

    }

    private fun openMenu(email: String? = null) {
        viewAction = SignInAction.OpenMenu(email)
    }

    private fun startLoadingAuth() {
        viewState = viewState.copy(isLoading = true, isError = false)
    }

    private fun onError(error: Exception) {

        val errorMessage = when (error) {
            is FirebaseAuthInvalidUserException -> R.string.auth_error_delete_user
            is FirebaseAuthInvalidCredentialsException -> R.string.auth_error_not_validation_password
            is FirebaseNotAuth -> R.string.auth_error_not_user
            is FirebaseNoEmailVerifications -> R.string.auth_error_not_email_verifications
            else -> R.string.auth_error_internet
        }
        viewState = viewState.copy(isLoading = false, isError = true, errorMessage = errorMessage)
    }
}