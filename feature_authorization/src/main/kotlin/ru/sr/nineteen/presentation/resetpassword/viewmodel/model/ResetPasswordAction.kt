package ru.sr.nineteen.presentation.resetpassword.viewmodel.model

sealed interface ResetPasswordAction {
    object OpenResetDialog : ResetPasswordAction
    object OpenSignInScreen : ResetPasswordAction
}