package ru.sr.nineteen.presentation.resetpassword.viewmodel.model

sealed interface ResetPasswordEvent {
    object OnClickSendPassword : ResetPasswordEvent
    object OnClearEmail : ResetPasswordEvent
    object OnResetAction : ResetPasswordEvent
    object OnStartSignInScreen : ResetPasswordEvent
    class OnChangeEmail(val email: String) : ResetPasswordEvent
}