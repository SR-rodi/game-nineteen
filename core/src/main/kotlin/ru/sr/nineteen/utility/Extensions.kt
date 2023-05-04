package ru.sr.nineteen.utility

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.StatusItem


fun List<GameItem>.checkNumberAndStatus(
    first: Int,
    second: Int,
    itemList: MutableList<GameItem>,
): Boolean {

    val a = (itemList[first].number == itemList[second].number
            || itemList[first].number + itemList[second].number == 10)


    val b = (itemList[first].statusItem != StatusItem.CHOICE
            && itemList[second].statusItem != StatusItem.CHOICE)

    return a && b
}
