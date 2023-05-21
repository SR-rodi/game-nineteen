package ru.sr.nineteen.domain.usecase

import ru.sr.nineteen.domain.model.AuthUserDomainModel

interface CreateUserWithEmailAndPasswordUseCase {

    suspend fun create(email: String, password: String): AuthUserDomainModel
}