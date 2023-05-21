package ru.sr.nineteen.domain.usecase

import ru.sr.nineteen.domain.model.ProfileUserDomainModel

interface GetUserInfoUseCase {

    suspend fun getInfo():ProfileUserDomainModel
}