package ru.sr.nineteen.domain.usecase.impl

import com.google.firebase.auth.FirebaseUser
import ru.sr.nineteen.domain.repository.AuthRepository
import ru.sr.nineteen.domain.usecase.CreateUserWithEmailAndPasswordUseCase

class CreateUserWithEmailAndPasswordUseCaseImpl(
    private val repository: AuthRepository,
) : CreateUserWithEmailAndPasswordUseCase {

    override suspend fun create(email: String, password: String) =
        repository.createUserWithEmailAndPassword(email, password)

}