package ru.sr.nineteen.domain.logic

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.LocationStatus

class Helper : BaseLogic() {

    fun getHelpPosition(items: List<GameItem>): Pair<Int, Int> {
        var pairPosition = Pair(0, 0)
        for (first in 0 until items.size - 1) {
            for (second in first + 1 until items.size) {
                if (locationStatus(first, second, items) != LocationStatus.PASS) {
                    pairPosition = Pair(first, second)
                    break
                }
            }
            if (pairPosition.first != pairPosition.second) break
        }
        return pairPosition
    }
}