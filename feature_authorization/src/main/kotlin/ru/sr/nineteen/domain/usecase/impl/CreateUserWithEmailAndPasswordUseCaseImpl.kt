package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.AuthUserRepository
import ru.sr.nineteen.domain.model.AuthUserDomainModel
import ru.sr.nineteen.domain.repository.AuthRepository
import ru.sr.nineteen.domain.usecase.CreateUserWithEmailAndPasswordUseCase

class CreateUserWithEmailAndPasswordUseCaseImpl(
    private val repository: AuthRepository,
    private val userRepository: AuthUserRepository,
) : CreateUserWithEmailAndPasswordUseCase {

    override suspend fun create(email: String, password: String): AuthUserDomainModel {

        val user = repository.createUserWithEmailAndPassword(email, password)
        userRepository.createUser(user)
        return user
    }

}