package ru.sr.mimeteen.remotedatabase.model

data class RatingDto(
    val userId: String = "",
    val userName: String? = null,
    val steps: Int = 0,
    val mode: String = "",
    val userAvatar: String? = null,
    val time: Long = 0L,
    val pointer: Int? = null,
)