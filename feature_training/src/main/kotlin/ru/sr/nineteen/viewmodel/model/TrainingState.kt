package ru.sr.nineteen.viewmodel.model

import ru.sr.nineteen.gameitem.GameItemEngine

data class TrainingState(
    val isError: Boolean = false,
    val items: List<List<GameItemEngine>> = emptyList(),
)