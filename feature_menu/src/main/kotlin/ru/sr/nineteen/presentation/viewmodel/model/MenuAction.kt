package ru.sr.nineteen.presentation.viewmodel.model

import ru.sr.nineteen.domain.gameitem.SettingGame

sealed interface MenuAction {
    class OpenGame(settingGame: SettingGame) : MenuAction
    object OpenTraining : MenuAction
    object OpenRating : MenuAction
}