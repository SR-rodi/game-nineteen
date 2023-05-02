package ru.sr.nineteen.domain.gameitem

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.sr.nineteen.data.gameitem.StatusItem

@Parcelize
class GameItem(
    val number: Int,
    var statusItem: StatusItem
) : Parcelable