package ru.sr.nineteen.data.repository

import ru.sr.mimeteen.remotedatabase.api.AuthApi
import ru.sr.nineteen.data.mapper.AuthDomainMapper
import ru.sr.nineteen.domain.model.AuthUserDomainModel
import ru.sr.nineteen.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val domainMapper: AuthDomainMapper,
) : AuthRepository {
    override suspend fun getCurrentUser() = api.getCurrentUser()

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): AuthUserDomainModel {
        val user = api.createUserWithEmailAndPassword(email, password)
        return domainMapper.userDtoToAuthUserDomainModel(user)
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): AuthUserDomainModel {

        val user = api.signInWithEmailAndPassword(email, password)

        return domainMapper.userDtoToAuthUserDomainModel(user)
    }

    override suspend fun resetPassword(email: String) {
        api.resetPassword(email)
    }

}

