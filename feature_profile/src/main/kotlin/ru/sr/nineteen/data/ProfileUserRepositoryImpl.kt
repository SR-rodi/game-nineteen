package ru.sr.nineteen.data


import ru.sr.mimeteen.remotedatabase.UserApi
import ru.sr.nineteen.data.mapper.ProfileDomainMapper
import ru.sr.nineteen.domain.model.ProfileUserDomainModel
import ru.sr.nineteen.domain.repository.ProfileUserRepository

class ProfileUserRepositoryImpl(
    private val api: UserApi,
    private val domainMapper: ProfileDomainMapper,
) : ProfileUserRepository {

    override suspend fun getCurrentUser(): ProfileUserDomainModel =
        domainMapper.userDtoToProfileUserDomainModel(api.getCurrentUser())

    override suspend fun updateUserName(newName: String) {
        api.changeUserName(newName)
    }

    override suspend fun updateUserAvatar(avatar: String) {
        api.changeUserAvatar(avatar)
    }

    override suspend fun updatePassword(newPassword: String) {
        api.updatePassword(newPassword)
    }

    override suspend fun logOut() {
        api.logOut()
    }

    override suspend fun deleteProfile() {
        api.deleteUser()
    }


}