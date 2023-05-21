package ru.sr.nineteen.domain.usecase

import ru.sr.nineteen.domain.model.AuthUserDomainModel

interface SignInWithEmailUseCase {

    suspend fun signIn(email: String,password:String): AuthUserDomainModel
}