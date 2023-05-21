package ru.sr.nineteen.engin

import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.gameitem.Position

interface GameEngin {

    fun createGameFieldByGameMode(mode: GameMode): List<List<GameItemEngine>>

    fun selectItem(
        items: List<List<GameItemEngine>>,
        position: Position,
    ): List<List<GameItemEngine>>

    fun choiceItems(
        items: List<List<GameItemEngine>>,
        position: Position,
    ): List<List<GameItemEngine>>?

    fun helpButton(items: List<List<GameItemEngine>>): List<List<GameItemEngine>>

    fun deleteItems(items: List<List<GameItemEngine>>): List<List<GameItemEngine>>

    fun addList(items: List<List<GameItemEngine>>): List<List<GameItemEngine>>

    companion object {
        fun create(): GameEngin {
            return GameEnginImpl()
        }
    }
}

