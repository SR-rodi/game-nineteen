package ru.sr.nineteen.engin.changeitems

import ru.sr.nineteen.engin.LocationIdentifier
import ru.sr.nineteen.engin.reWriteItemsHelp
import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.LocationStatus
import ru.sr.nineteen.gameitem.Position

internal object GameHelper : LocationIdentifier() {

    fun helpButton(items: List<List<GameItemEngine>>): List<List<GameItemEngine>> {
        val helpPosition = getHelpPosition(items)
        var newItems = items.toMutableList()

        if (helpPosition != null) {
            newItems = newItems.reWriteItemsHelp(helpPosition)
        }

        return newItems
    }

    private fun getHelpPosition(items: List<List<GameItemEngine>>): Pair<Position, Position>? {

        var pairPosition: Pair<Position, Position>? = null
        val listAllPosition = mutableListOf<Position>()
        for (first in 0..items.lastIndex) {
            for (second in 0..items[first].lastIndex) {
                listAllPosition.add((Position(first, second)))
            }
        }

        for (firstPosition in 0..listAllPosition.lastIndex) {

            for (secondPosition in firstPosition..listAllPosition.lastIndex) {
                if (locationStatus(
                        listAllPosition[firstPosition],
                        listAllPosition[secondPosition],
                        items
                    ) != LocationStatus.PASS
                ) {
                    pairPosition =
                        Pair(listAllPosition[firstPosition], listAllPosition[secondPosition])
                    break
                }
            }
            if (pairPosition != null) break
        }
        return pairPosition
    }
}