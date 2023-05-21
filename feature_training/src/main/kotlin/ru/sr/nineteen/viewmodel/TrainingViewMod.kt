package ru.sr.nineteen.viewmodel

import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.engin.GameEngin
import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.GameMode

class TrainingViewMod(
    private val gameEngin: GameEngin,
) : BaseViewModel<TrainingState, TrainingAction, TrainingEvent>(TrainingState()) {

    init {
        crateList(0)
    }

    override fun obtainEvent(viewEvent: TrainingEvent) {
        when (viewEvent) {
            is TrainingEvent.OnClickNextButton -> crateList(viewEvent.screenNumber)
            TrainingEvent.OnClickSkipButton -> openMenu()
        }
    }

    private fun openMenu() {
        viewAction = TrainingAction.OpenBack
    }

    private fun crateList(screenNumber: Int){
        viewState =
            viewState.copy(items = gameEngin.createGameFieldByGameMode( GameMode.Training.values()[screenNumber]))
    }

}

data class TrainingState(
    val isError: Boolean = false,
    val items: List<List<GameItemEngine>> = emptyList(),
)

sealed interface TrainingAction {
    object OpenBack : TrainingAction
}


sealed interface TrainingEvent {
    object OnClickSkipButton : TrainingEvent
    class OnClickNextButton(val screenNumber: Int) : TrainingEvent

}