package ru.sr.nineteen.domain

interface UserIdProvider{

    fun putUserId(token: String)

    fun clearUserId()

    fun getUserId(): String?

    fun userIdContain(): Boolean
}