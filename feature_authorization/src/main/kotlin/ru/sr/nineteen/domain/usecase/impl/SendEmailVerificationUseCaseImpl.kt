package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.AuthRepository
import ru.sr.nineteen.domain.usecase.SendEmailVerificationUseCase

class SendEmailVerificationUseCaseImpl(
    private val repository: AuthRepository,
) : SendEmailVerificationUseCase {
    override suspend fun send() {
        repository.sendEmailVerification()
    }
}