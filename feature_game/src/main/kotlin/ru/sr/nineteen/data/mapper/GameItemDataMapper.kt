package ru.sr.nineteen.data.mapper

import ru.sr.nineteen.domain.model.GameItemDomainModel
import ru.sr.nineteen.gameitem.GameItemEngine

interface GameItemDataMapper {
    fun engineToDomain(items: List<List<GameItemEngine>>): List<List<GameItemDomainModel>>
    fun domainToEngine(items: List<List<GameItemDomainModel>>): List<List<GameItemEngine>>
}

