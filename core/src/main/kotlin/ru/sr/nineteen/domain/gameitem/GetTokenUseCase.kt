package ru.sr.nineteen.domain.gameitem

import ru.sr.nineteen.domain.TokenProvider

interface GetTokenUseCase {

    fun getToken(): String?

}

class GetTokenUseCaseImpl(private val tokenProvider: TokenProvider) : GetTokenUseCase {
    override fun getToken() = tokenProvider.getToken()

}