package ru.sr.nineteen.viewmodel.model

sealed interface TrainingEvent {
    object OnClickSkipButton : TrainingEvent
    class OnClickNextButton(val screenNumber: Int) : TrainingEvent

}