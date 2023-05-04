package ru.sr.nineteen.domaine

import ru.sr.nineteen.domain.gameitem.GameItemEngine

interface ItemsRepository {

    fun createClassicListItems():List<List<GameItemEngine>>
}