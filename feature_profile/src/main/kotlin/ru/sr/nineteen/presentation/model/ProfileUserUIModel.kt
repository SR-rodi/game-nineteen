package ru.sr.nineteen.presentation.model

import android.net.Uri

data class ProfileUserUIModel(
    val email: String = "",
    val id: String = "",
    val name: String? = null,
    val avatar: Uri? = null,
)