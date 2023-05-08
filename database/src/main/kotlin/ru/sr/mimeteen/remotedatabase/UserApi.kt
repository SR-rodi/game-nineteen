package ru.sr.mimeteen.remotedatabase

import ru.sr.mimeteen.remotedatabase.model.UserDto

interface UserApi {

    suspend fun getCurrentUser(): UserDto

    suspend fun deleteUser()

    suspend fun changeUserName(name:String)

    suspend fun changeUserAvatar(avatar:String)

    suspend fun updatePassword(newPassword:String)

    suspend fun logOut()

}