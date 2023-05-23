package ru.sr.nineteen.presentation.viewmodel.model

sealed interface RatingAction {
    object GoToBack : RatingAction
    object OpenSignIn : RatingAction
}