package ru.sr.nineteen.presentation.edit.viewmodel

import android.util.Log
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.usecase.UpdateUserNameUseCase

class EditUserNameViewModel(
    private val updateUserNameUseCase: UpdateUserNameUseCase,
) : BaseViewModel<EditNameState, EditNameAction, EditNameEvent>(EditNameState()) {
    override fun obtainEvent(viewEvent: EditNameEvent) {
        when (viewEvent) {
            is EditNameEvent.OnChangeName -> onChangeName(viewEvent.mewName)
            EditNameEvent.OnClickBackButton -> onGoBack()
            EditNameEvent.OnClickSaveButton -> onClickSave()
            EditNameEvent.OnResetAction -> onResetAction()
            is EditNameEvent.OnStartScreen -> onStartScreen(viewEvent.userName)
        }
    }

    private fun onStartScreen(userName: String?) {
        viewState = viewState.copy(userName = userName ?: "")
    }

    private fun onClickSave() = scopeLaunch(
        onSuccess = ::onSuccess, onError = ::onError, onLoading = ::onLoading
    ) {
        updateUserNameUseCase.update(viewState.userName)
    }


    private fun onGoBack(newName: String? = null) {
        viewAction = EditNameAction.GoBack(newName)
    }

    private fun onChangeName(newName: String) {
        viewState = viewState.copy(userName = newName)
    }

    private fun onLoading() {
        viewState = viewState.copy(isLoading = true, isError = false)
    }

    private fun onError(e: Exception) {
        Log.e("Kart", "error = $e")
        viewState = viewState.copy(isLoading = false, isError = true)
    }

    private fun onSuccess() {
        viewState = viewState.copy(isLoading = false, isError = false)
        onGoBack(viewState.userName)
    }
}

data class EditNameState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val userName: String = "",
)

sealed interface EditNameAction {
    class GoBack(val newName: String?) : EditNameAction

}

sealed interface EditNameEvent {
    object OnClickSaveButton : EditNameEvent
    object OnClickBackButton : EditNameEvent
    class OnChangeName(val mewName: String) : EditNameEvent
    object OnResetAction : EditNameEvent
    class OnStartScreen(val userName: String?) : EditNameEvent
}