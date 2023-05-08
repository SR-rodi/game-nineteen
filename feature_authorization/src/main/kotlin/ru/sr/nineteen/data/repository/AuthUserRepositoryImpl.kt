package ru.sr.nineteen.data.repository

import ru.sr.mimeteen.remotedatabase.UserApi
import ru.sr.mimeteen.remotedatabase.model.UserDto
import ru.sr.nineteen.domain.model.AuthUserDomainModel
import ru.sr.nineteen.domain.repository.AuthUserRepository

class AuthUserRepositoryImpl(private val api: UserApi) : AuthUserRepository {

    override suspend fun createUser(user: AuthUserDomainModel) {
        api.createUser(UserDto(email = user.email,id = user.token))
    }


}