package ru.sr.nineteen.presentation.signin.viewmodel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.data.mapper.AuthUiMapper
import ru.sr.nineteen.domain.usecase.SignInWithEmailUseCase

class SignInViewModel(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val uiMapper: AuthUiMapper,
) : BaseViewModel<SignInState, SignInAction, SignInEvent>(SignInState()) {

    override fun obtainEvent(viewEvent: SignInEvent) {
        when (viewEvent) {
            is SignInEvent.OnClickRegistrationButton -> openRegistrationScreen(viewState.email)
            SignInEvent.OnClickSignInButton -> startAuth(viewState.email, viewState.password)
            SignInEvent.OnClickSkipAuthButton -> openMenu()
            is SignInEvent.OnChangeEmail -> onChangeEmail(viewEvent.email)
            is SignInEvent.OnChangePassword -> onChangePassword(viewEvent.password)
            SignInEvent.OnClearEmail -> onClearEmail()
            SignInEvent.OnClickForgotPasswordButton -> onOpenResetPasswordScreen()
            SignInEvent.OnOpenWarning -> openWarning()
        }
    }

    private fun openWarning() {
    }

    private fun onOpenResetPasswordScreen() {
        viewAction = SignInAction.OpenResetPassword
    }

    private fun onClearEmail() {
        viewState = viewState.copy(email = "")
    }

    private fun onChangeEmail(email: String) {
        viewState = viewState.copy(email = email)
    }

    private fun onChangePassword(password: String) {
        viewState = viewState.copy(password = password)
    }

    private fun openRegistrationScreen(email: String) {
        viewAction = SignInAction.OpenRegistration(email)
    }

    private fun startAuth(email: String, password: String) {
        scopeLaunch(
            context = Dispatchers.IO,
            onLoading = ::startLoadingAuth,
            onSuccess = ::successAuth,
            onError = ::onError
        ) {
            val user = uiMapper
                .authUserDomainModelToAuthUser(signInWithEmailUseCase.signIn(email, password))
            openMenu(user.email)
        }

    }

    private fun openMenu(email: String? = null) {
        viewAction = SignInAction.OpenMenu(email)
    }

    private fun startLoadingAuth() {
        viewState = viewState.copy(isLoading = true, isError = false)
    }

    private fun successAuth() {
        viewState = viewState.copy(isLoading = false)
    }

    private fun onError(error: Exception) {
        viewState = viewState.copy(isLoading = false, isError = true)

    }

}

sealed interface SignInAction {
    object OpenResetPassword : SignInAction
    class OpenMenu(email: String?) : SignInAction
    class OpenRegistration(email: String) : SignInAction

}

sealed interface SignInEvent {
    object OnClickSignInButton : SignInEvent
    object OnClickRegistrationButton : SignInEvent
    class OnChangePassword(val password: String) : SignInEvent
    class OnChangeEmail(val email: String) : SignInEvent
    object OnClickSkipAuthButton : SignInEvent
    object OnClearEmail : SignInEvent
    object OnClickForgotPasswordButton : SignInEvent
    object OnOpenWarning : SignInEvent
}

data class SignInState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val email: String = "",
    val password: String = "",
)
