package ru.sr.nineteen.domain.model

data class RatingDomainModel(
    val userId: String ,
    val userName: String?,
    val steps: Int,
    val mode: String ,
    val userAvatar: String? ,
    val time: Long,
    val pointer: Int?,
)