package ru.sr.mimeteen.remotedatabase.api.impl

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.tasks.await
import ru.sr.mimeteen.remotedatabase.api.UserApi
import ru.sr.mimeteen.remotedatabase.model.UserDto
import ru.sr.nineteen.data.FirebaseNotAuth

class FirebaseUserApi(
    private val auth: FirebaseAuth,
) : UserApi {

    override suspend fun getCurrentUser(): UserDto {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
        Log.e("Kart", "photoUri = ${currentUser.photoUrl}")
        return UserDto(
            email = currentUser.email,
            id = currentUser.uid,
            name = currentUser.displayName,
            photoUri = currentUser.photoUrl
        )
    }

    override suspend fun deleteUser() {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
        currentUser.delete().await()
    }

    override suspend fun changeUserName(name: String) {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
        val profileUpdates = userProfileChangeRequest { displayName = name }
        currentUser.updateProfile(profileUpdates).await()
    }

    override suspend fun changeUserAvatar(avatar: Uri) {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
        val profileUpdates = userProfileChangeRequest { photoUri = avatar }
        currentUser.updateProfile(profileUpdates).await()
    }

    override suspend fun updatePassword(oldPassword:String,newPassword: String) {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
        val credential = EmailAuthProvider
            .getCredential(currentUser.email!!, oldPassword)
        currentUser.reauthenticate(credential).await()
        currentUser.updatePassword(newPassword).await()
    }

    override suspend fun logOut() {
        auth.signOut()
    }
}