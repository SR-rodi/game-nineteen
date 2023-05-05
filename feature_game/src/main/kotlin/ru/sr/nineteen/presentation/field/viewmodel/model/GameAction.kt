package ru.sr.nineteen.presentation.field.viewmodel.model

sealed interface GameAction{
    object GoToBackStack: GameAction
    object OpenWinScreen : GameAction
    object SaveWinIfo : GameAction
}