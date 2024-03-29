package ru.sr.nineteen.gameitem

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameItemEngine(
    val number: Int,
    var statusItem: StatusItem
):Parcelable