package ru.sr.nineteen.presentation.viewmodel.model

sealed interface MenuEvent {
    object OnClickNextButton:MenuEvent
    object OnClickRandomButton:MenuEvent
    object OnClickClassicButton:MenuEvent
    object OnClickTrainingButton:MenuEvent
    object OnClickRatingButton:MenuEvent
    object ResetActions:MenuEvent
    object OnClickProfileButton : MenuEvent
}