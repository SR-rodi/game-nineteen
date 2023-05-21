package ru.sr.nineteen.domain.model

import android.net.Uri

class ProfileUserDomainModel(
    val email: String? = "",
    val id: String = "",
    val name: String? = "",
    val avatar: Uri? = null,
)