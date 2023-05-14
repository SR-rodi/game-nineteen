package ru.sr.nineteen.presentation.viewmodel.model

import ru.sr.nineteen.gameitem.GameMode

sealed interface MenuAction {
    class OpenGame(val gameMode: GameMode) : MenuAction
    object OpenTraining : MenuAction
    object OpenRating : MenuAction
    object OpenProfile : MenuAction
}