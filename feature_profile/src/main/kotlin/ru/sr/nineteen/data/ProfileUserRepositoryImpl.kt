package ru.sr.nineteen.data

import com.google.firebase.auth.FirebaseAuth
import ru.sr.mimeteen.remotedatabase.UserApi
import ru.sr.nineteen.data.mapper.ProfileDomainMapper
import ru.sr.nineteen.domain.UserIdProvider
import ru.sr.nineteen.domain.model.ProfileUserDomainModel
import ru.sr.nineteen.domain.repository.ProfileUserRepository

class ProfileUserRepositoryImpl(
    private val userIdProvider: UserIdProvider,
    private val api: UserApi,
    private val auth: FirebaseAuth,
    private val domainMapper: ProfileDomainMapper,
) : ProfileUserRepository {

    override suspend fun getUserById(): ProfileUserDomainModel {
        return domainMapper.userDtoToProfileUserDomainModel(api.getUserByUUid(userIdProvider.getUserId()!!))
    }

    override suspend fun updateUser() {
    }

    override suspend fun logOut() {
        userIdProvider.clearUserId()
    }

    override suspend fun deleteProfile() {

    }

}