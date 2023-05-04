package ru.sr.nineteen.utility

import android.util.Log
import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem

fun List<List<GameItemEngine>>.checkNumberAndStatus(
    firstPosition: Pair<Int, Int>,
    secondPosition: Pair<Int, Int>,
): Boolean {

    val itemOne = this[firstPosition.first][firstPosition.second]
    val itemTwo = this[secondPosition.first][secondPosition.second]

    val a = itemOne.number == itemTwo.number || itemOne.number + itemTwo.number == 10

    val b = itemOne.statusItem != StatusItem.CHOICE
            && itemTwo.statusItem != StatusItem.CHOICE

    return a && b
}


fun List<List<GameItemEngine>>.reWriteItems(
    listPositions: List<Pair<Int, Int>>,
    isChoiceItems: Boolean,
): MutableList<List<GameItemEngine>> {
    val lists = this.toMutableList()
    val itemsOne = lists[listPositions.first().first].toMutableList()
    val itemsTwo = lists[listPositions.last().first].toMutableList()
    val isOneLine = listPositions.first().first == listPositions.last().first

    if (isChoiceItems) {
        if (isOneLine) {
            itemsOne[listPositions.first().second] =
                itemsOne[listPositions.first().second].copy(statusItem = StatusItem.CHOICE)
            itemsOne[listPositions.last().second] =
                itemsOne[listPositions.last().second].copy(statusItem = StatusItem.CHOICE)

        } else {
            itemsOne[listPositions.first().second] =
                itemsOne[listPositions.first().second].copy(statusItem = StatusItem.CHOICE)
            itemsTwo[listPositions.last().second] =
                itemsTwo[listPositions.last().second].copy(statusItem = StatusItem.CHOICE)

        }
    } else {
        if (isOneLine) {
            itemsOne[listPositions.first().second] =
                itemsOne[listPositions.first().second].copy(statusItem = StatusItem.NOT_CHOICE)
            itemsOne[listPositions.last().second] =
                itemsOne[listPositions.last().second].copy(statusItem = StatusItem.NOT_CHOICE)
        } else {
            itemsOne[listPositions.first().second] =
                itemsOne[listPositions.first().second].copy(statusItem = StatusItem.NOT_CHOICE)
            itemsTwo[listPositions.last().second] =
                itemsTwo[listPositions.last().second].copy(statusItem = StatusItem.NOT_CHOICE)
        }
    }

    if (isOneLine) lists[listPositions.first().first] = itemsOne
    else {
        lists[listPositions.first().first] = itemsOne
        lists[listPositions.last().first] = itemsTwo
    }
    return lists
}
