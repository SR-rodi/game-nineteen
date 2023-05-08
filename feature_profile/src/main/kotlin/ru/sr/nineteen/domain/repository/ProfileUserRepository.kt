package ru.sr.nineteen.domain.repository

import ru.sr.nineteen.domain.model.ProfileUserDomainModel

interface ProfileUserRepository {

    suspend fun getUserById(): ProfileUserDomainModel

    suspend fun updateUser()

    suspend fun logOut()

    suspend fun deleteProfile()
}