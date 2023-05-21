package ru.sr.mimeteen.remotedatabase.model

import android.net.Uri
import com.google.firebase.auth.FirebaseUser

data class UserDto(
    val email: String? = null,
    val id: String = "",
    val name: String? = null,
    val photoUri: Uri? = null,
)

fun FirebaseUser.toUserDto() = UserDto(
    email = this.email,
    name = this.displayName,
    photoUri = this.photoUrl,
    id = this.uid
)

