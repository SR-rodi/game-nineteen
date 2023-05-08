package ru.sr.mimeteen.remotedatabase

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import ru.sr.mimeteen.remotedatabase.model.TableRemoteDatabase
import ru.sr.mimeteen.remotedatabase.model.UserDto

class FirebaseUserApi(
    database: FirebaseDatabase,
) : UserApi {

    private val userTable by lazy {
        database.getReference(TableRemoteDatabase.UserTable.name)
    }

    override suspend fun createUser(newUser: UserDto) {
        userTable.child(newUser.id).push().setValue(newUser)
    }

    override suspend fun getUserByUUid(uuid: String): UserDto {

        return userTable.child(uuid)
            .get()
            .await()
            .children
            .first()
            .getValue(UserDto::class.java) ?: throw NullPointerException("Not response user")

    }

    override suspend fun deleteUserByUUid(uuid: String) {
        userTable.child(uuid).removeValue().await()
    }

    suspend fun deleteUser(uuid: String) {

    }
}