package ru.sr.mimeteen.remotedatabase.model

import android.icu.text.Transliterator.Position

data class RatingDto(
    val userId: String = "",
    val userName: String? = null,
    val position: Int = 0,
    val step: Int = 0,
    val mode: String = "",
    val userAvatar: String? = null,
    val time: Long= 0L,
)