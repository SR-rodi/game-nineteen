package ru.sr.nineteen.domain.usecase.impl

import android.net.Uri
import android.util.Log
import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.UpdateUserNameUseCase

class UpdateUserNameCaseImpl(private val repository: ProfileUserRepository) : UpdateUserNameUseCase {

    override suspend fun update(name: String) {
        repository.updateUserName(name)
    }

    private suspend fun getUriNewAvatar(userId: String, uri: Uri?): Uri {
        val a = repository.uploadAvatar(userId, uri)
        Log.e("Kart", "path = ${a.path}")
        Log.e("Kart", a.toString())
        return a
    }

}