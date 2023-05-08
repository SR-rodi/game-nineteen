package ru.sr.mimeteen.remotedatabase.model

import android.net.Uri

data class UserDto(
    val email: String? = null,
    val id: String = "",
    val name: String? = null,
    val photoUri: Uri? = null

)