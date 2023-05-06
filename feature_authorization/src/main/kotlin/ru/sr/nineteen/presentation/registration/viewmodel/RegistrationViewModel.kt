package ru.sr.nineteen.presentation.registration.viewmodel

import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.data.mapper.AuthUiMapper
import ru.sr.nineteen.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import java.lang.Error

class RegistrationViewModel(
    private val createUserWithEmailAndPasswordUseCase: CreateUserWithEmailAndPasswordUseCase,
    private val uiMapper: AuthUiMapper,
) : BaseViewModel<RegistrationState, RegistrationAction, RegistrationEvent>(RegistrationState()) {

    override fun obtainEvent(viewEvent: RegistrationEvent) {
        when (viewEvent) {
            RegistrationEvent.OnClickBackStackButton -> onClickBackStackButton()
            is RegistrationEvent.OnRegistrationButtonClick ->
                startRegistration(viewEvent.email, viewEvent.password)

            is RegistrationEvent.OnStartScreen -> onStartScreen(viewEvent.email)
        }
    }

    private fun onStartScreen(email: String) {
        viewState = viewState.copy(email =email)
    }

    private fun onClickBackStackButton() {
        viewAction = RegistrationAction.GoToStack
    }

    private fun startRegistration(email: String, password: String) {
        scopeLaunch(onSuccess = ::onSuccess, onError = ::onError, onLoading = ::onLoading) {
            createUserWithEmailAndPasswordUseCase.create(email, password)
            viewAction = RegistrationAction.OpenSuccessRegistration
        }
    }

    private fun onLoading() {
        viewState = viewState.copy(isLoading = true, isError = false)
    }

    private fun onError(error: Exception) {
        viewState = viewState.copy(isLoading = false, isError = true)
    }

    private fun onSuccess(): () -> Unit = {
        viewState = viewState.copy(isLoading = false, isError = false)
    }

}

sealed interface RegistrationAction {
    object GoToStack : RegistrationAction
    object OpenSuccessRegistration : RegistrationAction
}

sealed interface RegistrationEvent {
    class OnRegistrationButtonClick(val email: String, val password: String) : RegistrationEvent
    object OnClickBackStackButton : RegistrationEvent
    class OnStartScreen(val email: String) : RegistrationEvent

}

data class RegistrationState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val email: String =""
)