package com.example.nineteen_2_0.data.gameitem

import android.os.Parcelable
import com.example.nineteen_2_0.data.itemlist.ClassicItemList
import com.example.nineteen_2_0.data.itemlist.CreateItemList
import kotlinx.android.parcel.Parcelize

@Parcelize
class GameItem(
    val number: Int,
    var statusItem: StatusItem
) : Parcelable