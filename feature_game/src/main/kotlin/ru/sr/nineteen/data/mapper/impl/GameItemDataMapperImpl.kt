package ru.sr.nineteen.data.mapper.impl

import ru.sr.nineteen.data.mapper.GameItemDataMapper
import ru.sr.nineteen.domain.model.GameItemDomainModel
import ru.sr.nineteen.gameitem.GameItemEngine

class GameItemDataMapperImpl : GameItemDataMapper {

    override fun engineToDomain(items: List<List<GameItemEngine>>): List<List<GameItemDomainModel>> {
        return items.map { lists ->
            lists.map { item ->
                GameItemDomainModel(
                    number = item.number,
                    statusItem = item.statusItem
                )
            }
        }
    }

    override fun domainToEngine(items: List<List<GameItemDomainModel>>): List<List<GameItemEngine>> {
        return items.map { lists ->
            lists.map { item ->
                GameItemEngine(
                    number = item.number,
                    statusItem = item.statusItem
                )
            }
        }
    }

}