package ru.sr.nineteen.presentation.win.viewmodel.model

import ru.sr.nineteen.domain.gameitem.SettingGame

sealed interface WinEvent {
    object OnRatingButtonClick : WinEvent
    object OnMenuButtonClick : WinEvent
    object OnResetAction : WinEvent
    class OnStartScreen(val settingGame: SettingGame) : WinEvent
}