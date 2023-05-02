package ru.sr.nineteen.logic

import ru.sr.nineteen.data.gameitem.GameItem
import ru.sr.nineteen.data.gameitem.StatusItem
import ru.sr.nineteen.utility.checkNumberAndStatus
import kotlin.math.abs

abstract class BaseLogic(private val itemList: MutableList<GameItem>) {

    protected fun locationStatus(first: Int, second: Int): LocationStatus {
        return when (itemList.checkNumberAndStatus(first, second, itemList)) {
            true -> checkPosition(first, second)
            false -> LocationStatus.PASS
        }
    }

    private fun checkPosition(first: Int, second: Int) =
        if (checkNear(first, second) == LocationStatus.NEAR) LocationStatus.NEAR
        else if (chekHorizontal(first, second) == LocationStatus.HORIZONTAL) LocationStatus.HORIZONTAL
        else if (chekVertical(first, second) == LocationStatus.VERTICAL) LocationStatus.VERTICAL
        else LocationStatus.PASS

    private fun checkNear(first: Int, second: Int) =
        when (first + 1 == second || first + 9 == second) {
            true -> LocationStatus.NEAR
            false -> LocationStatus.PASS
        }

    private fun chekHorizontal(first: Int, second: Int): LocationStatus {
        var counter = 0
        for (i in first + 1 until second) {
            if (itemList[i].statusItem == StatusItem.CHOICE) counter++
            else break
        }
        return when (abs(first - second) - 1 == counter) {
            true -> LocationStatus.HORIZONTAL
            false -> LocationStatus.PASS
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
            true -> LocationStatus.VERTICAL
            false -> LocationStatus.PASS
        }
    }
}