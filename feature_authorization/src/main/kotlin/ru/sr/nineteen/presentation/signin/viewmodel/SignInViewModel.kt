package ru.sr.nineteen.presentation.signin.viewmodel

import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.data.mapper.AuthUiMapper
import ru.sr.nineteen.domain.usecase.SignInWithEmailUseCase

class SignInViewModel(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val uiMapper: AuthUiMapper,
) : BaseViewModel<AuthState, AuthAction, AuthEvent>(AuthState()) {

    override fun obtainEvent(viewEvent: AuthEvent) {
        when (viewEvent) {
            is AuthEvent.OnClickRegistrationButton -> openRegistrationScreen(viewEvent.email)
            is AuthEvent.OnClickSignInButton -> startAuth(viewEvent.email, viewEvent.password)
            AuthEvent.OnClickSkipAuthButton -> openMenu()
        }
    }

    private fun openRegistrationScreen(email: String) {
        viewAction = AuthAction.OpenRegistration(email)
    }

    private fun startAuth(email: String, password: String) {
        scopeLaunch(
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
        viewAction = AuthAction.OpenMenu(email)
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

sealed interface AuthAction {
    class OpenMenu(email: String?) : AuthAction
    class OpenRegistration(email: String) : AuthAction

}

sealed interface AuthEvent {
    class OnClickSignInButton(val email: String, val password: String) : AuthEvent
    class OnClickRegistrationButton(val email: String) : AuthEvent
    object OnClickSkipAuthButton : AuthEvent

}

data class AuthState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
