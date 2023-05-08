package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.UpdateUserUseCase

class UpdateUserUseCaseImpl(private val repository: ProfileUserRepository) : UpdateUserUseCase {
    override suspend fun update() {
    }
}