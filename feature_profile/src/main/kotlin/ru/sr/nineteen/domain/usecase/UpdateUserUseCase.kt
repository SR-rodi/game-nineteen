package ru.sr.nineteen.domain.usecase

interface UpdateUserUseCase {

    suspend fun update(name:String?,avatar:String?)
}