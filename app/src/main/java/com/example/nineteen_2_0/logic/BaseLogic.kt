package com.example.nineteen_2_0.logic

import com.example.nineteen_2_0.checkNumberAndStatus
import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.StatusItem
import com.example.nineteen_2_0.logic.LocationStatus.*
import kotlin.math.abs

abstract class BaseLogic(private val itemList: MutableList<GameItem>) {

    protected fun locationStatus(first: Int, second: Int): LocationStatus {
        return when (itemList.checkNumberAndStatus(first, second, itemList)) {
            true -> checkPosition(first, second)
            false -> PASS
        }
    }

    private fun checkPosition(first: Int, second: Int) =
        if (checkNear(first, second) == NEAR) NEAR
        else if (chekHorizontal(first, second) == HORIZONTAL) HORIZONTAL
        else if (chekVertical(first, second) == VERTICAL) VERTICAL
        else PASS

    private fun checkNear(first: Int, second: Int) =
        when (first + 1 == second || first + 9 == second) {
            true -> NEAR
            false -> PASS
        }

    private fun chekHorizontal(first: Int, second: Int): LocationStatus {
        var counter = 0
        for (i in first + 1 until second) {
            if (itemList[i].statusItem == StatusItem.CHOICE) counter++
            else break
        }
        return when (abs(first - second) - 1 == counter) {
            true -> HORIZONTAL
            false -> PASS
        }
    }

    private fun chekVertical(first: Int, second: Int): LocationStatus {
        var counter = 0
        if ((second - first) % 9 == 0)
            for (i in 1..second / 9 - first / 9) {
                if (itemList[first + (9 * i)].statusItem == StatusItem.CHOICE) counter++
                else break
            }
        return when (counter > 0 && counter == (second / 9 - first / 9) - 1) {
            true -> VERTICAL
            false -> PASS
        }
    }
}