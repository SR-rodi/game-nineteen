package ru.sr.nineteen.presentation.registration.viewmodel

import android.util.Log
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.domain.Validation
import ru.sr.nineteen.domain.usecase.CreateUserWithEmailAndPasswordUseCase

class RegistrationViewModel(
    private val createUserWithEmailAndPasswordUseCase: CreateUserWithEmailAndPasswordUseCase,
    private val validation: Validation,
) : BaseViewModel<RegistrationState, RegistrationAction, RegistrationEvent>(RegistrationState()) {

    override fun obtainEvent(viewEvent: RegistrationEvent) {
        when (viewEvent) {
            is RegistrationEvent.OnStartScreen -> onStartScreen(viewEvent.email)
            is RegistrationEvent.OnChangeEmail -> onChangeEmail(viewEvent.email)
            is RegistrationEvent.OnChangePassword -> onChangePassword(viewEvent.password)
            is RegistrationEvent.OnChangeRepeatPassword -> onChangeRepeatPassword(viewEvent.password)
            RegistrationEvent.OnClickBackStackButton -> onClickBackStackButton()
            RegistrationEvent.OnRegistrationButtonClick -> onClickRegistrationButton()
            RegistrationEvent.OnClearEmail -> onClearEmail()
            RegistrationEvent.OnResetAction -> RegistrationEvent.OnResetAction
            RegistrationEvent.OnStartSing -> viewAction = RegistrationAction.OpenStartScreen
        }
    }

    private fun onClickRegistrationButton() {

        val isPasswordValidation =
            validation.passwordValidation(viewState.password)
                    && viewState.password == viewState.repeatPassword
        viewState = viewState.copy(
            isErrorPasswordValidation = !isPasswordValidation,
            isErrorEmailValidation = !validation.emailValidation(viewState.email)
        )
        if (!viewState.isErrorEmailValidation && !viewState.isErrorPasswordValidation)
            startRegistration()

    }

    private fun onChangeRepeatPassword(password: String) {
        viewState = viewState.copy(repeatPassword = password)
    }

    private fun onChangePassword(password: String) {
        viewState = viewState.copy(password = password)
    }

    private fun onChangeEmail(email: String) {
        viewState = viewState.copy(email = email)
    }

    private fun onClearEmail() {
        viewState = viewState.copy(email = "")
    }

    private fun onStartScreen(email: String) {
        viewState = viewState.copy(email = email)
    }

    private fun onClickBackStackButton() {
        viewAction = RegistrationAction.GoToStack
    }

    private fun startRegistration() {
        scopeLaunch(onSuccess = ::onSuccess, onError = ::onError, onLoading = ::onLoading) {
            createUserWithEmailAndPasswordUseCase.create(viewState.email, viewState.password)
            viewAction = RegistrationAction.OpenSuccessRegistration
        }
    }

    private fun onLoading() {
        viewState = viewState.copy(isLoading = true, isError = false)
    }

    private fun onError(error: Exception) {
      if (error is FirebaseAuthUserCollisionException){
          viewState = viewState.copy(errorMessage = R.string.auth_error_internet)
      }
        viewState = viewState.copy(isLoading = false, isError = true)
    }

    private fun onSuccess(): () -> Unit = {
        viewState = viewState.copy(isLoading = false, isError = false)
    }

}

sealed interface RegistrationAction {
    object GoToStack : RegistrationAction
    object OpenSuccessRegistration : RegistrationAction
    object OpenStartScreen : RegistrationAction {

    }
}

sealed interface RegistrationEvent {
    object OnRegistrationButtonClick : RegistrationEvent
    object OnClickBackStackButton : RegistrationEvent
    object OnClearEmail : RegistrationEvent
    object OnResetAction : RegistrationEvent
    object OnStartSing : RegistrationEvent
    class OnStartScreen(val email: String) : RegistrationEvent
    class OnChangeEmail(val email: String) : RegistrationEvent
    class OnChangePassword(val password: String) : RegistrationEvent
    class OnChangeRepeatPassword(val password: String) : RegistrationEvent

}

data class RegistrationState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val errorMessage: Int = R.string.auth_error_internet,
    val isErrorEmailValidation: Boolean = false,
    val isErrorPasswordValidation: Boolean = false,
)