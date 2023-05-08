package ru.sr.nineteen.domain

import ru.sr.nineteen.domain.gameitem.GameItemEngine

interface ItemsRepository {

    fun createClassicListItems():List<List<GameItemEngine>>
}