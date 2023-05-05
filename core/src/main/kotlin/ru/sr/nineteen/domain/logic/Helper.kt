package ru.sr.nineteen.domain.logic

import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.LocationStatus
import ru.sr.nineteen.domain.gameitem.Position

class Helper : BaseLogic() {

    fun getHelpPosition(items: List<List<GameItemEngine>>): Pair<Position, Position>? {

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
                    pairPosition = Pair(listAllPosition[firstPosition],listAllPosition[secondPosition])
                    break
                }
            }
            if (pairPosition!=null) break
        }
        return pairPosition
    }
}