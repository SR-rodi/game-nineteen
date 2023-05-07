package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.AuthRepository
import ru.sr.nineteen.domain.usecase.PutTokenUseCase

class PutTokenUseCaseImpl(private val authRepository: AuthRepository):PutTokenUseCase {

    override fun putToken(token: String) {
        authRepository.setToken(token)
    }
}