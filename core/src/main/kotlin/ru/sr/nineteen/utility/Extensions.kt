package ru.sr.nineteen.utility

import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.Position
import ru.sr.nineteen.domain.gameitem.StatusItem

fun List<List<GameItemEngine>>.checkNumberAndStatus(
    firstPosition: Position,
    secondPosition: Position,
): Boolean {

    val itemOne = this[firstPosition.row][firstPosition.column]
    val itemTwo = this[secondPosition.row][secondPosition.column]

    val a = itemOne.number == itemTwo.number || itemOne.number + itemTwo.number == 10

    val b = itemOne.statusItem != StatusItem.CHOICE
            && itemTwo.statusItem != StatusItem.CHOICE

    return a && b
}


fun List<List<GameItemEngine>>.reWriteItemsChoiceOrNotChoice(
    listPositions: List<Position>,
    isChoiceItems: Boolean,
): MutableList<List<GameItemEngine>> {
    val lists = this.toMutableList()
    val itemsOne = lists[listPositions.first().row].toMutableList()
    val itemsTwo = lists[listPositions.last().row].toMutableList()
    val isOneLine = listPositions.first().row == listPositions.last().row

    if (isChoiceItems) {
        itemsOne[listPositions.first().column] =
            itemsOne[listPositions.first().column].copy(statusItem = StatusItem.CHOICE)
        if (isOneLine) {
            itemsOne[listPositions.last().column] =
                itemsOne[listPositions.last().column].copy(statusItem = StatusItem.CHOICE)

        } else {
            itemsTwo[listPositions.last().column] =
                itemsTwo[listPositions.last().column].copy(statusItem = StatusItem.CHOICE)

        }
    } else {
        itemsOne[listPositions.first().column] =
            itemsOne[listPositions.first().column].copy(statusItem = StatusItem.NOT_CHOICE)
        if (isOneLine) {
            itemsOne[listPositions.last().column] =
                itemsOne[listPositions.last().column].copy(statusItem = StatusItem.NOT_CHOICE)
        } else {
            itemsTwo[listPositions.last().column] =
                itemsTwo[listPositions.last().column].copy(statusItem = StatusItem.NOT_CHOICE)
        }
    }
    lists[listPositions.first().row] = itemsOne

    if (!isOneLine) lists[listPositions.last().row] = itemsTwo

    return lists
}
