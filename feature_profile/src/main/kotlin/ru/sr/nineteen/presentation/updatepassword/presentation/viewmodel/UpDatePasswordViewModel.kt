package ru.sr.nineteen.presentation.updatepassword.presentation.viewmodel

import android.util.Log
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.Validation
import ru.sr.nineteen.domain.usecase.ChangePasswordUseCase

class UpDatePasswordViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val validation: Validation,
) : BaseViewModel<UpdatePasswordState, UpdatePasswordAction, UpdatePasswordEvent>(
    UpdatePasswordState()
) {
    override fun obtainEvent(viewEvent: UpdatePasswordEvent) {
        when (viewEvent) {
            is UpdatePasswordEvent.OnChangeNewPassword -> viewState =
                viewState.copy(newPassword = viewEvent.newPassword, isErrorNewPassword = false)

            is UpdatePasswordEvent.OnChangeOldPassword -> {
                val isErrorNewPassword = validation.passwordValidation(viewEvent.oldPassword)
                viewState =
                    viewState.copy(
                        oldPassword = viewEvent.oldPassword,
                        isErrorNewPassword = isErrorNewPassword
                    )
            }

            UpdatePasswordEvent.OnClickBackButton -> viewAction = UpdatePasswordAction.OnGoBack
            UpdatePasswordEvent.OnClickUpdatePassword -> onClickUpdatePassword()
            UpdatePasswordEvent.OnResetAction -> {
                viewState = viewState.copy(
                    newPassword = "",
                    oldPassword = "",
                    isLoading = false,
                    isError = false,
                    isErrorNewPassword = false,
                    isErrorOldPassword = false
                )
                onResetAction()
            }
        }
    }

    private fun onClickUpdatePassword() {
        scopeLaunch(
            onSuccess = ::onSuccess, onError = ::onError, onLoading = ::onLoading
        ) {
            changePasswordUseCase.update(viewState.oldPassword, viewState.newPassword)
        }
    }

    private fun onLoading() {
        viewState = viewState.copy(isLoading = true, isError = false)
    }

    private fun onError(error: Exception) {
        Log.e("Kart", "error = $error")
        when (error) {
            is FirebaseAuthInvalidCredentialsException -> viewState =
                viewState.copy(isErrorOldPassword = true)

            else -> viewState.isError
        }
        viewState = viewState.copy(isLoading = false, isError = true)
    }

    private fun onSuccess() {
        viewState = viewState.copy(isLoading = false, isError = false)
        viewAction = UpdatePasswordAction.OnGoBack
    }
}

sealed interface UpdatePasswordEvent {
    class OnChangeNewPassword(val newPassword: String) : UpdatePasswordEvent
    class OnChangeOldPassword(val oldPassword: String) : UpdatePasswordEvent
    object OnClickUpdatePassword : UpdatePasswordEvent
    object OnClickBackButton : UpdatePasswordEvent
    object OnResetAction : UpdatePasswordEvent

}

sealed interface UpdatePasswordAction {
    object OnGoBack : UpdatePasswordAction
}

data class UpdatePasswordState(
    val newPassword: String = "",
    val oldPassword: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isErrorNewPassword: Boolean = false,
    val isErrorOldPassword: Boolean = false,
)