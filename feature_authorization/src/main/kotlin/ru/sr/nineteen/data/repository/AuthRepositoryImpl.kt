package ru.sr.nineteen.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import ru.sr.nineteen.data.mapper.AuthDomainMapper
import ru.sr.nineteen.domain.model.AuthUserDomainModel
import ru.sr.nineteen.domain.TokenProvider
import ru.sr.nineteen.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val domainMapper: AuthDomainMapper,
    private val tokenProvider: TokenProvider,
) : AuthRepository {
    override suspend fun getCurrentUser() = auth.currentUser

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): AuthUserDomainModel {
        val user = auth.createUserWithEmailAndPassword(email, password).await().user
            ?: throw FirebaseNotAuth()
        return domainMapper.firebaseUserToAuthUserDomainModel(user)
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): AuthUserDomainModel {

        val user = auth.signInWithEmailAndPassword(email, password).await().user
            ?: throw FirebaseNotAuth()

        return if (user.isEmailVerified)
            domainMapper.firebaseUserToAuthUserDomainModel(user)
        else throw FirebaseNoEmailVerifications()
    }

    override suspend fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override fun setToken(token: String) {
        tokenProvider.putToken(token)
    }

}

