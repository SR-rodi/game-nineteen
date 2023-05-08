package ru.sr.mimeteen.remotedatabase

import ru.sr.mimeteen.remotedatabase.model.UserDto

interface UserApi {

    suspend fun createUser(newUser: UserDto)

}