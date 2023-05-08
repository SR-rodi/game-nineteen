package ru.sr.nineteen.domain.usecase.impl

import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.UpdateUserUseCase

class UpdateUserUseCaseImpl(private val repository: ProfileUserRepository) : UpdateUserUseCase {

    override suspend fun update(name: String?, avatar: String?) {
        when {
            name == null && avatar != null -> repository.updateUserAvatar(avatar)
            name != null && avatar == null -> repository.updateUserName(name)
            name !=null && avatar!=null ->{
                repository.updateUserAvatar(avatar)
                repository.updateUserName(name)
            }
            else -> throw NullPointerException("UpdateUserUseCaseImpl two values = null")
        }
    }

}