package ru.sr.nineteen.data.repository

import ru.sr.nineteen.data.mapper.GameItemDataMapper
import ru.sr.nineteen.domain.reposytory.GameRepository
import ru.sr.nineteen.domain.model.GameItemDomainModel
import ru.sr.nineteen.engin.GameEngin
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.gameitem.Position

class GameRepositoryImpl(
    private val gameEngin: GameEngin,
    private val dataMapper: GameItemDataMapper,
) : GameRepository {
    override fun createGameFieldByGameMode(mode: GameMode): List<List<GameItemDomainModel>> {
        return dataMapper.engineToDomain(gameEngin.createGameFieldByGameMode(mode))
    }

    override fun selectItem(
        items: List<List<GameItemDomainModel>>,
        position: Position,
    ): List<List<GameItemDomainModel>> {
        return dataMapper
            .engineToDomain(
                gameEngin.selectItem(
                    dataMapper.domainToEngine(items), position
                )
            )
    }

    override fun choiceItems(
        position: Position,
        items: List<List<GameItemDomainModel>>,
    ): List<List<GameItemDomainModel>>? {
        val newItems =
            gameEngin.choiceItems(dataMapper.domainToEngine(items), position) ?: return null
        return dataMapper.engineToDomain(newItems)
    }

    override fun helpButton(items: List<List<GameItemDomainModel>>): List<List<GameItemDomainModel>> {
        return dataMapper.engineToDomain(dataMapper.domainToEngine(items))
    }

    override fun deleteItems(items: List<List<GameItemDomainModel>>): List<List<GameItemDomainModel>> {
        return dataMapper.engineToDomain(gameEngin.deleteItems(dataMapper.domainToEngine(items)))
    }

    override fun addList(items: List<List<GameItemDomainModel>>): List<List<GameItemDomainModel>> {
        return dataMapper.engineToDomain(gameEngin.addList(dataMapper.domainToEngine(items)))
    }

}