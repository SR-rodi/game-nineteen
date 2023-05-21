package ru.sr.mimeteen.remotedatabase.api

import com.google.firebase.auth.FirebaseUser
import ru.sr.mimeteen.remotedatabase.model.UserDto

interface AuthApi {
    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun createUserWithEmailAndPassword(email:String, password:String): UserDto

    suspend fun signInWithEmailAndPassword(email:String, password:String): UserDto

    suspend fun resetPassword(email: String)

}

