package ru.sr.mimeteen.remotedatabase.api

import android.net.Uri
import ru.sr.mimeteen.remotedatabase.model.UserDto

interface UserApi {

    suspend fun getCurrentUser(): UserDto

    suspend fun deleteUser()

    suspend fun changeUserName(name:String)

    suspend fun changeUserAvatar(avatar: Uri)

    suspend fun updatePassword(oldPassword:String,newPassword:String)

    suspend fun logOut()

}