package ru.sr.nineteen.domain.usecase

interface ResetPasswordUseCase {

    suspend fun reset(email: String)
}