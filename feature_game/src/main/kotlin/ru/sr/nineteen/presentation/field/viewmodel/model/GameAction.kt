package ru.sr.nineteen.presentation.field.viewmodel.model

import ru.sr.nineteen.gameitem.SettingGame

sealed interface GameAction{
    object GoToBackStack: GameAction
    object OpenRating: GameAction
    class OpenWinScreen(val setting: ru.sr.nineteen.gameitem.SettingGame) : GameAction
    object SaveWinIfo : GameAction
}