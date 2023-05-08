package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.model.ProfileUserDomainModel
import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.GetUserInfoUseCase

class GetUserInfoUseCaseImpl(
    private val repository: ProfileUserRepository,
) : GetUserInfoUseCase {
    override suspend fun getInfo(): ProfileUserDomainModel {
        return repository.getUserById()
    }
}