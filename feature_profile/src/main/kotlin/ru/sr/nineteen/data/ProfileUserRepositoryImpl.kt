package ru.sr.nineteen.data


import android.net.Uri
import ru.sr.mimeteen.remotedatabase.api.UploadApi
import ru.sr.mimeteen.remotedatabase.api.UserApi
import ru.sr.nineteen.data.mapper.ProfileDomainMapper
import ru.sr.nineteen.domain.ByteConvertor
import ru.sr.nineteen.domain.model.ProfileUserDomainModel
import ru.sr.nineteen.domain.repository.ProfileUserRepository

class ProfileUserRepositoryImpl(
    private val api: UserApi,
    private val uploadApi: UploadApi,
    private val domainMapper: ProfileDomainMapper,
    private val byteConvertor: ByteConvertor,
) : ProfileUserRepository {

    override suspend fun getCurrentUser(): ProfileUserDomainModel =
        domainMapper.userDtoToProfileUserDomainModel(api.getCurrentUser())

    override suspend fun updateUserName(newName: String) {
        api.changeUserName(newName)
    }

    override suspend fun updateUserAvatar(avatar: Uri) {
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

    override suspend fun uploadAvatar(userId: String, uri: Uri?): Uri {
        if (uri == null) throw NullPointerException("uri не найден")

       return uploadApi.uLoadImage(userId, byteConvertor.fromUri(uri))
    }


}