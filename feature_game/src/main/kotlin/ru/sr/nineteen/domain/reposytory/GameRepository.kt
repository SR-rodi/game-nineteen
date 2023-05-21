package ru.sr.nineteen.domain.reposytory

import ru.sr.nineteen.domain.model.GameItemDomainModel
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.gameitem.Position

interface GameRepository {

    fun createGameFieldByGameMode(mode: GameMode): List<List<GameItemDomainModel>>

    fun selectItem(
        items: List<List<GameItemDomainModel>>,
        position: Position,
    ): List<List<GameItemDomainModel>>

    fun choiceItems(
        position: Position,
        items: List<List<GameItemDomainModel>>,
    ): List<List<GameItemDomainModel>>?

    fun helpButton(items: List<List<GameItemDomainModel>>): List<List<GameItemDomainModel>>

    fun deleteItems(items: List<List<GameItemDomainModel>>): List<List<GameItemDomainModel>>

    fun addList(items: List<List<GameItemDomainModel>>): List<List<GameItemDomainModel>>
}