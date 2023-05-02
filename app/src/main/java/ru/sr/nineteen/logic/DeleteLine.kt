package ru.sr.nineteen.logic

import ru.sr.nineteen.data.gameitem.GameItem
import ru.sr.nineteen.data.gameitem.StatusItem

class DeleteLine(private val itemList: MutableList<GameItem>) {

    fun delete(lineOne: Int, lineTwo: Int): MutableList<Int> {
        val deleteListLineOne = mutableListOf<Int>()
        val deleteListLineTwo = mutableListOf<Int>()
        val deleteList = mutableListOf<Int>()

        checkOneLine(lineOne, deleteListLineOne)
        if (lineOne != lineTwo) checkOneLine(lineTwo, deleteListLineTwo)

        if (deleteListLineTwo.size == 9) deleteLine(deleteListLineTwo, deleteList)

        if (deleteListLineOne.size == 9) deleteLine(deleteListLineOne, deleteList)

        return deleteList.ifEmpty { emptyList<Int>().toMutableList() }

    }

    private fun deleteLine(deleteListLineOne: MutableList<Int>, deleteList: MutableList<Int>) {
        deleteListLineOne.reversed().forEach { position ->
            itemList.removeAt(position)
            deleteList.add(position)
        }
    }

    private fun checkOneLine(
        line: Int,
        deleteListPosition: MutableList<Int>
    ) {
        val n = if (itemList.size > line * 9 + 8) line * 9 + 8
        else itemList.lastIndex

        for (i in line * 9..n) {
            if (itemList[i].statusItem == StatusItem.CHOICE || itemList[i].statusItem == StatusItem.NOT_VISIBLE)
                deleteListPosition.add(i)
            else break
        }
    }

}