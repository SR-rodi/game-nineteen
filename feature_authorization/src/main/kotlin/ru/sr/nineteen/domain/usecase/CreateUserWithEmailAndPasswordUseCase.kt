package ru.sr.nineteen.domain.usecase

import com.google.firebase.auth.FirebaseUser
import ru.sr.nineteen.domain.AuthUserDomainModel

interface CreateUserWithEmailAndPasswordUseCase {

    suspend fun create(email: String, password: String): AuthUserDomainModel
}