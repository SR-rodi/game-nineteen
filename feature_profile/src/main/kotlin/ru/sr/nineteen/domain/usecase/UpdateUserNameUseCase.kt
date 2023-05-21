package ru.sr.nineteen.domain.usecase

import android.net.Uri

interface UpdateUserNameUseCase {

    suspend fun update(name:String)
}

