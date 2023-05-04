package ru.sr.nineteen.domain.logic

import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem

class DeleteLine() {

    fun delete(lineOne: Int, lineTwo: Int,items: List<GameItemEngine>): List<Int> {
        val deleteListLineOne = mutableListOf<Int>()
        val deleteListLineTwo = mutableListOf<Int>()
        val deleteList = mutableListOf<Int>()
        val itemList = items.toMutableList()

        checkOneLine(lineOne, deleteListLineOne,itemList)
        if (lineOne != lineTwo) checkOneLine(lineTwo, deleteListLineTwo,itemList)

        if (deleteListLineTwo.size == 9) deleteLine(deleteListLineTwo, deleteList,itemList)

        if (deleteListLineOne.size == 9) deleteLine(deleteListLineOne, deleteList,itemList)

        return deleteList

    }

    private fun deleteLine(deleteListLineOne: MutableList<Int>, deleteList: MutableList<Int>,items: MutableList<GameItemEngine>) {
        deleteListLineOne.reversed().forEach { position ->
            items.removeAt(position)
            deleteList.add(position)
        }
    }

    private fun checkOneLine(
        line: Int,
        deleteListPosition: MutableList<Int>,
        items: MutableList<GameItemEngine>
    ) {
        val n = if (items.size > line * 9 + 8) line * 9 + 8
        else items.lastIndex

        for (i in line * 9..n) {
            if (items[i].statusItem == StatusItem.CHOICE || items[i].statusItem == StatusItem.NOT_VISIBLE)
                deleteListPosition.add(i)
            else break
        }
    }

}