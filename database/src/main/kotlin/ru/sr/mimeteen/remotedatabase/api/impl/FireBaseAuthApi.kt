package ru.sr.mimeteen.remotedatabase.api.impl

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import ru.sr.mimeteen.remotedatabase.api.AuthApi
import ru.sr.mimeteen.remotedatabase.model.UserDto
import ru.sr.mimeteen.remotedatabase.model.toUserDto
import ru.sr.mimeteen.remotedatabase.FirebaseNoEmailVerifications
import ru.sr.mimeteen.remotedatabase.FirebaseNotAuth

internal class FireBaseAuthApi(private val auth: FirebaseAuth):AuthApi {

    override suspend fun getCurrentUser() = auth.currentUser

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): UserDto {
        val user = auth.createUserWithEmailAndPassword(email, password).await().user
            ?: throw FirebaseNotAuth()
        user.sendEmailVerification()
        return user.toUserDto()
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): UserDto {

        val user = auth.signInWithEmailAndPassword(email, password).await().user
            ?: throw FirebaseNotAuth()

        return if (user.isEmailVerified)
            user.toUserDto()
        else throw FirebaseNoEmailVerifications()
    }

    override suspend fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

}