package ru.sr.mimeteen.remotedatabase

import com.google.firebase.database.FirebaseDatabase
import ru.sr.mimeteen.remotedatabase.model.TableRemoteDatabase
import ru.sr.mimeteen.remotedatabase.model.UserDto

class FirebaseUserApi(database: FirebaseDatabase) : UserApi {

    private val userTable by lazy {
        database.getReference(TableRemoteDatabase.UserTable.name)
    }

    override suspend fun createUser(newUser: UserDto) {
        userTable.push().setValue(newUser)
    }


}