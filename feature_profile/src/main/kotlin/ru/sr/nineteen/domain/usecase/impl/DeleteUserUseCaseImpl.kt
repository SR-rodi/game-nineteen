package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.DeleteUserUseCase

class DeleteUserUseCaseImpl(private val repository: ProfileUserRepository):DeleteUserUseCase {
    override suspend fun delete() {
        repository.deleteProfile()
    }
}