package ru.sr.nineteen.presentation.viewmodel.model

import android.net.Uri

data class MenuState(
    val isNextEnable: Boolean = false,
    val userAvatar: Uri? = null
)