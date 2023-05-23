package ru.sr.nineteen.presentation.viewmodel.model

sealed interface RatingEvent {
    object OnStartScreen : RatingEvent
    object OnClickBackStack : RatingEvent
    object OnResetAction : RatingEvent
    object OnClickShowMyResultButton : RatingEvent
}