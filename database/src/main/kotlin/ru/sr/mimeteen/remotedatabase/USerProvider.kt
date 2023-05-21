package ru.sr.mimeteen.remotedatabase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ru.sr.mimeteen.remotedatabase.model.UserDto

interface UserProvider {

    fun getUser(): UserDto

}

class UserProviderImpl(private val auth: FirebaseAuth) : UserProvider {
    override fun getUser(): UserDto {
        val user = auth.currentUser
        return UserDto(
            email = user?.email,
            id = user?.uid ?: "",
            name = user?.displayName,
            photoUri = user?.photoUrl

        )
    }

}