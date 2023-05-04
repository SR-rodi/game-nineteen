package ru.sr.nineteen.presentation.viewmodel.model

sealed interface GameAction{
    object GoToBackStack:GameAction
    object OpenWinScreen : GameAction
}