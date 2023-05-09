package ru.sr.nineteen.presentation.profile.viewmodel

import android.net.Uri
import kotlinx.coroutines.delay
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.usecase.GetUserInfoUseCase
import ru.sr.nineteen.domain.usecase.LogOutUseCase
import ru.sr.nineteen.domain.usecase.UpdateUserAvatarUseCase
import ru.sr.nineteen.presentation.model.ProfileUserUIModel
import ru.sr.nineteen.presentation.model.mapper.ProfileUIMapper

class ProfileViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val updateUserAvatarUseCase: UpdateUserAvatarUseCase,
    private val uiMapper: ProfileUIMapper,
) : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>(ProfileState()) {

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            ProfileEvent.OnClickDeleteOutButton -> onClickDeleteButton()
            ProfileEvent.OnClickLogOutButton -> onExit()
            ProfileEvent.OnClickUserName -> onClickUserName()
            ProfileEvent.OnResetAction -> onResetAction()
            ProfileEvent.OnStartScreen -> onStart()
            ProfileEvent.OnClickAvatar -> onClickAvatar()
            is ProfileEvent.OnSetNewAvatar -> onSetNewAvatar(viewEvent.uri)
            ProfileEvent.OnClickSaveAvatar -> onClickSaveAvatar()
            is ProfileEvent.OnChangeName -> viewState =
                viewState.copy(user = viewState.user.copy(name = viewEvent.newName))

            ProfileEvent.OnClickUpdatePasswordButton -> viewAction = ProfileAction.OpenUpdatePasswordScreen
        }

    }

    private fun onClickUserName() {
        viewAction = ProfileAction.OpenEditNameScreen(viewState.user.name)
    }

    private fun onClickSaveAvatar() {
        scopeLaunch {
            viewState = viewState.copy(isUpLoadAvatar = true)
            updateUserAvatarUseCase.update(viewState.user.id, viewState.avatarUri)
            viewState = viewState.copy(isSaveButtonVisibility = false)
            delay(100)
            viewState = viewState.copy(isUpLoadAvatar = false)
        }
    }

    private fun onSetNewAvatar(uri: Uri?) {
        if (uri != null)
            viewState = viewState.copy(avatarUri = uri, isSaveButtonVisibility = true)
    }

    private fun onClickAvatar() {
        viewAction = ProfileAction.OpenGallery
    }

    private fun onClickDeleteButton() {
        viewAction = ProfileAction.OpenWarningScreen
    }

    private fun onExit() = scopeLaunch(
        onSuccess = ::onSuccess, onError = ::onError, onLoading = ::onLoading
    ) {
        logOutUseCase.logOut()
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
    object OpenWarningScreen : ProfileAction
    object OpenGallery : ProfileAction
    object OpenUpdatePasswordScreen : ProfileAction
    class OpenEditNameScreen(val userName: String?) : ProfileAction
}

sealed interface ProfileEvent {
    class OnSetNewAvatar(val uri: Uri?) : ProfileEvent
    class OnChangeName(val newName: String) : ProfileEvent
    object OnStartScreen : ProfileEvent
    object OnClickLogOutButton : ProfileEvent
    object OnClickDeleteOutButton : ProfileEvent
    object OnClickAvatar : ProfileEvent
    object OnClickUserName : ProfileEvent
    object OnResetAction : ProfileEvent
    object OnClickSaveAvatar : ProfileEvent
    object OnClickUpdatePasswordButton : ProfileEvent

}

data class ProfileState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val user: ProfileUserUIModel = ProfileUserUIModel(),
    val avatarUri: Uri? = null,
    val isSaveButtonVisibility: Boolean = false,
    val isUpLoadAvatar: Boolean = false,
)