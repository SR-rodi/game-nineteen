package ru.sr.nineteen.gameitem

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SettingGame(
    var gameMode: GameMode.Game = GameMode.Game.Classic,
    var list: List<List<GameItemEngine>> = emptyList(),
    var time: Long = 0L,
    var stepCount: Int = 0,
) : Parcelable