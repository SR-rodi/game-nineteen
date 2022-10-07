package com.example.nineteen_2_0.data.gameitem

import android.os.Parcelable
import com.example.nineteen_2_0.data.itemlist.ClassicItemList
import kotlinx.android.parcel.Parcelize

@Parcelize
class SettingGame(
    var list: List<GameItem> = ClassicItemList().create(),
    var time: Int = 0,
    var stepCount: Int = 0
) : Parcelable