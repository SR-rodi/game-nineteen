package ru.sr.nineteen.domain.gameitem

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GameItem(
    val number: Int,
    var statusItem: StatusItem
) : Parcelable