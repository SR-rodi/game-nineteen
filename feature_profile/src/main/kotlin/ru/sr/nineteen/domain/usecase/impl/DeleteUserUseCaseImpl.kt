package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.DeleteProfileUserUseCase

class DeleteUserUseCaseImpl(private val repository: ProfileUserRepository):DeleteProfileUserUseCase {
    override suspend fun delete() {
        repository.deleteProfile()
    }
}