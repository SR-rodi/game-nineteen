package ru.sr.mimeteen.remotedatabase.model

data class UserDto(
    val email: String?=null,
    val id: String="",
    val name: String? = null,
)