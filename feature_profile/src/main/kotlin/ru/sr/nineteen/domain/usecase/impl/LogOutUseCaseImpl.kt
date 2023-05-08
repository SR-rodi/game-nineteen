package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.LogOutUseCase

class LogOutUseCaseImpl(private val repository: ProfileUserRepository) : LogOutUseCase {
    override suspend fun logOut() {
        repository.logOut()
    }

}