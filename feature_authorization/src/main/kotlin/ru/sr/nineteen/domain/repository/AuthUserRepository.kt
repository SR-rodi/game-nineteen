package ru.sr.nineteen.domain.repository

import ru.sr.nineteen.domain.model.AuthUserDomainModel

interface AuthUserRepository {

    suspend fun createUser(user:AuthUserDomainModel)
}
