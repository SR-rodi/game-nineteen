package ru.sr.nineteen.presentation.win.viewmodel

import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.presentation.win.viewmodel.model.WinAction
import ru.sr.nineteen.presentation.win.viewmodel.model.WinEvent
import ru.sr.nineteen.presentation.win.viewmodel.model.WinState

class WinViewModel : BaseViewModel<WinState, WinAction, WinEvent>(WinState()) {
    override fun obtainEvent(viewEvent: WinEvent) {
        when (viewEvent) {
            WinEvent.OnMenuButtonClick -> onMenuClick()
            WinEvent.OnRatingButtonClick -> onRatingClick()
            WinEvent.OnResetAction -> onResetAction()
            is WinEvent.OnStartScreen -> onStartScree(viewEvent.settingGame)
        }
    }

    private fun onStartScree(settingGame: SettingGame) {
        viewState =
            viewState.copy(
                time = settingGame.time.toString(),
                stepCount = settingGame.stepCount,
                gameMode = settingGame.gameMode
            )
    }

    private fun onRatingClick() {
        viewAction = WinAction.OpenRating
    }

    private fun onMenuClick() {
        viewAction = WinAction.OpenMenu
    }
}

