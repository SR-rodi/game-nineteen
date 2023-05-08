package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.data.ProfileUserRepositoryImpl
import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.ChangePasswordUseCase

class ChangePasswordUseCaseImpl(
    private val repository: ProfileUserRepository
): ChangePasswordUseCase {
    override suspend fun update(newPass: String) {
        repository.updatePassword(newPass)
    }
}