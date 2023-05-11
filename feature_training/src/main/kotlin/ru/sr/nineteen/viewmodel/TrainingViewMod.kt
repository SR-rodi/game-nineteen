package ru.sr.nineteen.viewmodel

import ru.sr.nineteen.BaseViewModel

class TrainingViewMod :
    BaseViewModel<TrainingState, TrainingAction, TrainingEvent>(TrainingState()) {

    override fun obtainEvent(viewEvent: TrainingEvent) {
        when (viewEvent) {

            else -> {}
        }
    }


}

data class TrainingState(
    val isError: Boolean = false,
)

sealed interface TrainingAction {

}


sealed interface TrainingEvent {

}