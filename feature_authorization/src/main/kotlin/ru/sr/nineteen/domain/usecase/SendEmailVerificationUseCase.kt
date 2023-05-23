package ru.sr.nineteen.domain.usecase

interface SendEmailVerificationUseCase {

    suspend fun send()
}