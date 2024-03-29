package ru.sr.nineteen.engin

import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.StatusItem
import ru.sr.nineteen.gameitem.Position

internal fun List<List<GameItemEngine>>.checkNumberAndStatus(
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


internal fun List<List<GameItemEngine>>.reWriteItemsChoiceOrNotChoice(
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

internal fun List<List<GameItemEngine>>.reWriteItemsHelp(
    pairPosition: Pair<Position, Position>,
): MutableList<List<GameItemEngine>> {
    val newItems = this.toMutableList()
    val listFirstItems = this[pairPosition.first.row].toMutableList()
    val listSecondItems = this[pairPosition.second.row].toMutableList()
    val isOnLine: Boolean = pairPosition.first.row == pairPosition.second.row

    listFirstItems[pairPosition.first.column] =
        listFirstItems[pairPosition.first.column].copy(statusItem = StatusItem.HELP)

    if (isOnLine)
        listFirstItems[pairPosition.second.column] =
            listFirstItems[pairPosition.second.column].copy(statusItem = StatusItem.HELP)
    else listSecondItems[pairPosition.second.column] =
        listSecondItems[pairPosition.second.column].copy(statusItem = StatusItem.HELP)

    newItems[pairPosition.first.row] = listFirstItems
    if (!isOnLine) newItems[pairPosition.second.row] = listSecondItems

    return newItems
}