package ru.sr.nineteen.presentation.signin.viewmodel.model

sealed interface SignInAction {
    object OpenWarningMessage : SignInAction
    class OpenResetPassword(val email: String) : SignInAction
    class OpenMenu(val email: String?) : SignInAction
    class OpenRegistration(val email: String) : SignInAction
}