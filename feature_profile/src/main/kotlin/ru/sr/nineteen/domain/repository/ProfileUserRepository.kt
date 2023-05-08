package ru.sr.nineteen.domain.repository

import ru.sr.nineteen.domain.model.ProfileUserDomainModel

interface ProfileUserRepository {

    suspend fun getCurrentUser(): ProfileUserDomainModel

    suspend fun updateUserName(newName: String)

    suspend fun updateUserAvatar(avatar: String)

    suspend fun updatePassword(newPassword: String)

    suspend fun logOut()

    suspend fun deleteProfile()
}