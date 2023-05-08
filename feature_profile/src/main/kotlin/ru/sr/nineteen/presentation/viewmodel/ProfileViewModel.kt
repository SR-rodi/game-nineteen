package ru.sr.nineteen.presentation.viewmodel

import android.util.Log
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.DeleteUserUseCase
import ru.sr.nineteen.domain.usecase.GetUserInfoUseCase
import ru.sr.nineteen.domain.usecase.LogOutUseCase
import ru.sr.nineteen.presentation.model.ProfileUserUIModel
import ru.sr.nineteen.presentation.model.mapper.ProfileUIMapper

class ProfileViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val uiMapper: ProfileUIMapper,
) : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>(ProfileState()) {

    init {
        scopeLaunch {
            val user =
                uiMapper.profileDomainToProfileUi(getUserInfoUseCase.getInfo())
        }

    }

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            ProfileEvent.OnClickAvatar -> {}
            ProfileEvent.OnClickDeleteOutButton -> onExit()
            ProfileEvent.OnClickLogOutButton -> onExit()
            ProfileEvent.OnClickUserName -> {}
            ProfileEvent.OnResetAction -> onResetAction()
            ProfileEvent.OnSaveButtonUserName -> {}
            ProfileEvent.OnStartScreen -> onStart()
        }

    }

    private fun onExit() = scopeLaunch(
        onSuccess = ::onSuccess, onError = ::onError, onLoading = ::onLoading
    ) {
        deleteUserUseCase.delete()
        viewAction = ProfileAction.OpenSignInScreen
    }

    private fun onStart() = scopeLaunch(
        onSuccess = ::onSuccess, onError = ::onError, onLoading = ::onLoading
    ) {
        val user = uiMapper.profileDomainToProfileUi(getUserInfoUseCase.getInfo())
        viewState = viewState.copy(user = user)
    }

    private fun onLoading() {
        viewState = viewState.copy(isLoading = true, isError = false)
    }

    private fun onError(e: Exception) {
        viewState = viewState.copy(isLoading = false, isError = true)
    }

    private fun onSuccess() {
        viewState = viewState.copy(isLoading = false, isError = false)
    }
}

sealed interface ProfileAction {
    object OpenSignInScreen : ProfileAction
}

sealed interface ProfileEvent {
    object OnStartScreen : ProfileEvent
    object OnClickLogOutButton : ProfileEvent
    object OnClickDeleteOutButton : ProfileEvent
    object OnClickAvatar : ProfileEvent
    object OnClickUserName : ProfileEvent
    object OnSaveButtonUserName : ProfileEvent
    object OnResetAction : ProfileEvent

}

data class ProfileState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val user: ProfileUserUIModel = ProfileUserUIModel(),
)