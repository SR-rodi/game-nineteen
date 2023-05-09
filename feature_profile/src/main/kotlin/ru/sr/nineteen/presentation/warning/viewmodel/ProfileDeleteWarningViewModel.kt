package ru.sr.nineteen.presentation.warning.viewmodel

import android.util.Log
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.usecase.DeleteProfileUserUseCase

class ProfileDeleteWarningViewModel (
    private val deleteUserUseCase: DeleteProfileUserUseCase
        ): BaseViewModel<ProfileDeleteState, ProfileDeleteAction, ProfileDeleteEvent>(
    ProfileDeleteState()
) {
    override fun obtainEvent(viewEvent: ProfileDeleteEvent) {
        when(viewEvent){
            ProfileDeleteEvent.OnClickNoButton -> popToBackStack()
            ProfileDeleteEvent.OnClickYesButton -> onDeleteProfile()
            ProfileDeleteEvent.OnResetAction -> onResetAction()
        }
    }

    private fun onDeleteProfile()  = scopeLaunch(
    onSuccess = ::onSuccess, onError = ::onError, onLoading = ::onLoading
    ){
        Log.e("Kart","Start")
        deleteUserUseCase.delete()

        Log.e("Kart","Stop")
    }

    private fun popToBackStack() {
        viewAction = ProfileDeleteAction.PopToBackStack
    }

    private fun onLoading() {
        viewState = viewState.copy(isLoading = true, isError = false)
    }

    private fun onError(e: Exception) {
        Log.e("Kart","error = $e")
        viewState = viewState.copy(isLoading = false, isError = true)
    }

    private fun onSuccess() {
        viewState = viewState.copy(isLoading = false, isError = false)
        viewAction = ProfileDeleteAction.OpenSignInScreen
    }
}

sealed interface ProfileDeleteEvent {
            object OnClickNoButton: ProfileDeleteEvent
            object OnClickYesButton: ProfileDeleteEvent
            object OnResetAction: ProfileDeleteEvent
}

sealed interface ProfileDeleteAction {
    object OpenSignInScreen: ProfileDeleteAction
    object PopToBackStack: ProfileDeleteAction

}

data class ProfileDeleteState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,

)