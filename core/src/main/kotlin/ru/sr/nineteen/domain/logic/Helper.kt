package ru.sr.nineteen.domain.logic

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.LocationStatus

class Helper(private val itemList: MutableList<GameItem>) : BaseLogic(itemList) {

    fun testHelper(): Pair<Int, Int> {
        var pairPosition = Pair(0, 0)
        for (first in 0 until itemList.size - 1) {
            for (second in first + 1 until itemList.size) {
                if (locationStatus(first, second) != LocationStatus.PASS){
                    pairPosition = Pair(first, second)
                    break
                }
            }
            if (pairPosition.first != pairPosition.second) break
        }
        return pairPosition
    }
}