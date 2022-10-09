package com.example.nineteen_2_0.logic

import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.StatusItem
import com.example.nineteen_2_0.logic.LocationStatus.*

class Helper(private val itemList: MutableList<GameItem>) : BaseLogic(itemList) {

    fun testHelper(): Pair<Int, Int> {
        var pairPosition = Pair(0, 0)
        for (first in 0 until itemList.size - 1) {
            for (second in first + 1 until itemList.size) {
                if (locationStatus(first, second) != PASS){
                    pairPosition = Pair(first, second)
                    break
                }
            }
            if (pairPosition.first != pairPosition.second) break
        }
        return pairPosition
    }
}