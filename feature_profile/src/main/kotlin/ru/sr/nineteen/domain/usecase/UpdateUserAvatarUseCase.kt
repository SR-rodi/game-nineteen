package ru.sr.nineteen.domain.usecase

import android.net.Uri

interface UpdateUserAvatarUseCase {

    suspend fun update(userId: String, avatarUri: Uri?)
}

