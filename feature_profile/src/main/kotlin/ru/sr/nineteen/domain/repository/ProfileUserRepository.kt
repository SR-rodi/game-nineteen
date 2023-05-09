package ru.sr.nineteen.domain.repository

import android.net.Uri
import ru.sr.nineteen.domain.model.ProfileUserDomainModel

interface ProfileUserRepository {

    suspend fun getCurrentUser(): ProfileUserDomainModel

    suspend fun updateUserName(newName: String)

    suspend fun updateUserAvatar(avatar: Uri)

    suspend fun updatePassword(newPassword: String)

    suspend fun logOut()

    suspend fun deleteProfile()

    suspend fun uploadAvatar(userId:String,uri: Uri?):Uri
}