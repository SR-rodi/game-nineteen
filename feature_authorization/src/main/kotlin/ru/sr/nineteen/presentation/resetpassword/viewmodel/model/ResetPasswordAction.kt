package ru.sr.nineteen.presentation.resetpassword.viewmodel.model

sealed interface ResetPasswordAction {
    object StartInfoMessage : ResetPasswordAction
}