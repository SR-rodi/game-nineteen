package ru.sr.nineteen.presentation.win.viewmodel.model

sealed interface WinAction {
    object OpenRating : WinAction
    object OpenMenu : WinAction
}