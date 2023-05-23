package ru.sr.nineteen.presentation.field.viewmodel.model

import ru.sr.nineteen.gameitem.SettingGame

sealed interface GameAction{
    object GoToBackStack: GameAction
    object OpenRating: GameAction
    class OpenWinScreen(val setting: SettingGame) : GameAction
    object SaveWinIfo : GameAction
    object ShowToastErrorRegistration : GameAction
}