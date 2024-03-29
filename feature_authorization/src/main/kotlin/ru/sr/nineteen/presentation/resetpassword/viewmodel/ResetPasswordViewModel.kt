package ru.sr.nineteen.presentation.resetpassword.viewmodel

import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.domain.Validation
import ru.sr.nineteen.domain.usecase.ResetPasswordUseCase
import ru.sr.nineteen.presentation.resetpassword.viewmodel.model.ResetPasswordAction
import ru.sr.nineteen.presentation.resetpassword.viewmodel.model.ResetPasswordEvent
import ru.sr.nineteen.presentation.resetpassword.viewmodel.model.ResetPasswordState

class ResetPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val validation: Validation,
) : BaseViewModel<ResetPasswordState, ResetPasswordAction, ResetPasswordEvent>(ResetPasswordState()) {
    override fun obtainEvent(viewEvent: ResetPasswordEvent) {
        when (viewEvent) {
            is ResetPasswordEvent.OnChangeEmail -> onChangeEmail(viewEvent.email)
            ResetPasswordEvent.OnClearEmail -> onChangeEmail("")
            ResetPasswordEvent.OnClickSendPassword -> onClickSendPassword()
            ResetPasswordEvent.OnResetAction -> onResetAction()
            ResetPasswordEvent.OnStartSignInScreen -> viewAction =ResetPasswordAction.OpenSignInScreen
        }
    }

    private fun sendEmail() = scopeLaunch(
        onLoading = ::onLoading,
        onError = ::onError,
        onSuccess = ::onSuccess
    ) {
        resetPasswordUseCase.reset(viewState.email)
    }


    private fun onClickSendPassword() {
        if (validation.emailValidation(email = viewState.email))
            sendEmail()
        else viewState = viewState.copy(isEmailValidation = false)


    }

    private fun onChangeEmail(email: String) {
        viewState = viewState.copy(email = email, isEmailValidation = true)
    }

    private fun onLoading() {
        viewState = viewState.copy(isLoading = true, isError = false, isEmailValidation = true)
    }

    private fun onError(e: Exception) {
        val messageId = when (e) {
            is FirebaseAuthInvalidUserException -> R.string.auth_error_delete_user
            is FirebaseTooManyRequestsException -> R.string.auth_error_unusual_activity
            else -> R.string.auth_error_internet
        }
        viewState = viewState.copy(
            isLoading = false,
            isError = true,
            isEmailValidation = true,
            errorMessageId = messageId
        )
    }

    private fun onSuccess() {
        viewAction = ResetPasswordAction.OpenResetDialog
        viewState = viewState.copy(isLoading = false, isError = false, isEmailValidation = true)
    }
}

