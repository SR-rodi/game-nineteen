package ru.sr.nineteen.domain.gameitem

import ru.sr.nineteen.domain.UserIdProvider

interface GetTokenUseCase {

    fun getToken(): String?

}

class GetTokenUseCaseImpl(private val tokenProvider: UserIdProvider) : GetTokenUseCase {
    override fun getToken() = tokenProvider.getUserId()

}