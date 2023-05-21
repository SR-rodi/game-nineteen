package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.AuthRepository
import ru.sr.nineteen.domain.usecase.ResetPasswordUseCase

class ResetPasswordUseCaseImpl(
    private val repository: AuthRepository,
) : ResetPasswordUseCase {

    override suspend fun reset(email: String) {
        repository.resetPassword(email)
    }

}