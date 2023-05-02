package ru.sr.nineteen.data.gameitem

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.ClassicItemList

@Parcelize
class SettingGame(
    var gameMode:String = "classic",
    var list: List<GameItem> = ClassicItemList().create(),
    var time: Int = 0,
    var stepCount: Int = 0
) : Parcelable