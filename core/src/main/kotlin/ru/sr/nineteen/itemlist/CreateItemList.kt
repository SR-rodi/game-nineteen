package ru.sr.nineteen.itemlist

import ru.sr.nineteen.domain.gameitem.GameItemEngine

abstract class CreateItemList {

    abstract fun create(): List<List<GameItemEngine>>

    val baseItem = mutableListOf(
        1,1
/*        1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 1, 1, 2, 1,
        3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9*/

    )
}