package ru.sr.nineteen.presentation.signin.viewmodel.model

sealed interface SignInEvent {
    object OnClickSignInButton : SignInEvent
    object OnClickRegistrationButton : SignInEvent
    class OnChangePassword(val password: String) : SignInEvent
    class OnChangeEmail(val email: String) : SignInEvent
    object OnClickSkipAuthButton : SignInEvent
    object OnClearEmail : SignInEvent
    object OnClickForgotPasswordButton : SignInEvent
    object OnOpenWarning : SignInEvent
    object OnResetAction: SignInEvent
    object OnResetState : SignInEvent
}