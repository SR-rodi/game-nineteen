package ru.sr.mimeteen.remotedatabase

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.tasks.await
import ru.sr.mimeteen.remotedatabase.model.TableRemoteDatabase
import ru.sr.mimeteen.remotedatabase.model.UserDto
import ru.sr.nineteen.data.FirebaseNotAuth

class FirebaseUserApi(
    private val auth: FirebaseAuth,
) : UserApi {

    override suspend fun getCurrentUser(): UserDto {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
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

    override suspend fun changeUserAvatar(avatar: String) {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
        val profileUpdates = userProfileChangeRequest { photoUri = Uri.parse(avatar) }
        currentUser.updateProfile(profileUpdates).await()
    }

    override suspend fun updatePassword(newPassword: String) {
        val currentUser = auth.currentUser ?: throw FirebaseNotAuth()
        currentUser.updatePassword(newPassword).await()
    }

    override suspend fun logOut() {
        auth.signOut()
    }
}


