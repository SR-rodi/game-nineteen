package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.model.AuthUserDomainModel
import ru.sr.nineteen.domain.repository.AuthRepository
import ru.sr.nineteen.domain.usecase.CreateUserWithEmailAndPasswordUseCase

class CreateUserWithEmailAndPasswordUseCaseImpl(
    private val repository: AuthRepository,
) : CreateUserWithEmailAndPasswordUseCase {

    override suspend fun create(email: String, password: String): AuthUserDomainModel {
        return repository.createUserWithEmailAndPassword(email, password)
    }

}