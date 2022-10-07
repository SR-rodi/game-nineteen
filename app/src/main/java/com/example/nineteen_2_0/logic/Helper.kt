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

    fun createHelper(itemList: MutableList<GameItem>): Pair<Int, Int> {
        var pairPosition = Pair(0, 0)

        for (i in 0 until itemList.size - 1) {
            pairPosition = checkLeft(itemList, i)
            if (pairPosition.first != pairPosition.second) break

            pairPosition = checkBottom(itemList, i)
            if (pairPosition.first != pairPosition.second) break

            pairPosition = checkLineOnString(itemList, i)
            if (pairPosition.first != pairPosition.second) break

            pairPosition = checkColumn(itemList, i)
            if (pairPosition.first != pairPosition.second) break

        }
        return pairPosition
    }

    private fun checkColumn(itemList: MutableList<GameItem>, i: Int): Pair<Int, Int> {
        var pairPosition = Pair(0, 0)
        if (itemList.size / 9 >= 3
            && itemList[i].statusItem == StatusItem.NOT_CHOICE
            && itemList.size > i + 9
        ) {
            for (j in 0 until (itemList.size / 9 - 1)) {
                if (itemList.size > (9 * j) + i + 9) {
                    if (itemList[(9 * j) + i].statusItem == StatusItem.CHOICE) {
                        if (itemList[i] == itemList[(9 * j) + i + 9] && i != (9 * j) + i + 9) {
                            pairPosition = Pair(i, (9 * j) + i + 9)
                            break
                        }
                    } else break
                }
            }
        }
        return pairPosition
    }

    private fun checkBottom(itemList: MutableList<GameItem>, i: Int): Pair<Int, Int> {
        var pairPosition = Pair(0, 0)
        if (itemList[i].statusItem == StatusItem.NOT_CHOICE && itemList.size > i + 9) {
            if (itemList[i + 9].statusItem == StatusItem.NOT_CHOICE) {
                if (itemList[i] == itemList[i + 9]
                    || itemList[i].number + itemList[i + 9].number == 10
                ) pairPosition = Pair(i, i + 9)
            }
        }
        return pairPosition
    }

    private fun checkLeft(itemList: MutableList<GameItem>, i: Int) =
        if (itemList[i].statusItem == StatusItem.NOT_CHOICE
            && itemList[i + 1].statusItem == StatusItem.NOT_CHOICE
        ) {
            if (itemList[i].number == itemList[i + 1].number
                || itemList[i].number + itemList[i + 1].number == 10
            ) Pair(i, i + 1)
            else Pair(0, 0)
        } else Pair(0, 0)

    private fun checkLineOnString(itemList: MutableList<GameItem>, i: Int): Pair<Int, Int> {
        var pairPosition = Pair(0, 0)
        if (itemList[i].statusItem == StatusItem.NOT_CHOICE && itemList[i + 1].statusItem == StatusItem.CHOICE) {
            for (j in i + 2 until itemList.size) {

                if (itemList[i] == itemList[j]
                    || itemList[i].number + itemList[j].number == 10
                ) {
                    pairPosition = Pair(i, j)
                    break
                } else break

            }
        }
        return pairPosition
    }
}