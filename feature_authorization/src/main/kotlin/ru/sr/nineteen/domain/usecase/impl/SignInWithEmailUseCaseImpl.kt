package ru.sr.nineteen.domain.usecase.impl

import com.google.firebase.auth.FirebaseUser
import ru.sr.nineteen.domain.repository.AuthRepository
import ru.sr.nineteen.domain.usecase.SignInWithEmailUseCase

class SignInWithEmailUseCaseImpl(
    private val repository: AuthRepository,
) : SignInWithEmailUseCase {

    override suspend fun signIn(email: String, password: String) =
        repository.signInWithEmailAndPassword(email, password)
}