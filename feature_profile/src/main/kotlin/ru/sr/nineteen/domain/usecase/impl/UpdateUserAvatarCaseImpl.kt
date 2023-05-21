package ru.sr.nineteen.domain.usecase.impl

import android.net.Uri
import android.util.Log
import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.UpdateUserAvatarUseCase
import ru.sr.nineteen.domain.usecase.UpdateUserNameUseCase

class UpdateUserAvatarCaseImpl(private val repository: ProfileUserRepository) :
    UpdateUserAvatarUseCase {


    override suspend fun update(userID: String, avatarUri: Uri?) {
       val getStorageUri =  repository.uploadAvatar(userID, avatarUri)
        repository.updateUserAvatar(getStorageUri)
    }

}