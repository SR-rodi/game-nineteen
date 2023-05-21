package ru.sr.nineteen.presentation.field.viewmodel.model

import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.GameMode

data class GameState(
    val isStartTamer: Boolean = true,
    val isWin: Boolean = false,
    val items: List<List<GameItemEngine>> = emptyList(),
    val timeCounter: Long = 0L,
    val stepCounter: Int = 1,
    val mode: GameMode.Game = GameMode.Game.Classic,
)
