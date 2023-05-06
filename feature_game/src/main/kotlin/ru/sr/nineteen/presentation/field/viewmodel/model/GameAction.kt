package ru.sr.nineteen.presentation.field.viewmodel.model

import ru.sr.nineteen.domain.gameitem.SettingGame

sealed interface GameAction{
    object GoToBackStack: GameAction
    class OpenWinScreen(val setting: SettingGame) : GameAction
    object SaveWinIfo : GameAction
}