package ru.sr.nineteen

import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.itemlist.CreateItemList

class ClassicItemList : CreateItemList() {

    override fun create(): List<List<GameItemEngine>> {
        val items = mutableListOf<MutableList<GameItemEngine>>()
        var numberIndex = 0
        for (row in 1..3) {
            val childItems = mutableListOf<GameItemEngine>()
            for (column in 1..COLUMN_SIZE) {
                if (baseItem.size>numberIndex)
                    childItems.add(GameItemEngine(baseItem[numberIndex], StatusItem.NOT_CHOICE))
                numberIndex++
            }
            items.add(childItems)
        }
        return items
    }

    companion object {
        private const val COLUMN_SIZE = 9
    }
}
