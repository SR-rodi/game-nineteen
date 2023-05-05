package ru.sr.nineteen.domain.logic

import android.util.Log
import ru.sr.nineteen.ClassicItemList
import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.domain.gameitem.LocationStatus
import ru.sr.nineteen.domain.gameitem.Position
import ru.sr.nineteen.utility.reWriteItems


class ClassicGameLogic : BaseLogic() {

    private val helper: Helper = Helper()
    private val deleteLine = DeleteLine()
    private var helpPosition = Pair(0, 0)
    private val positionList = mutableListOf<Position>()

    fun selectItems(
        list: List<List<GameItemEngine>>,
        position: Position,
    ): List<List<GameItemEngine>> {
        val lists = list.toMutableList()
        val items = lists[position.row].toMutableList()
        val status =
            if (items[position.column].statusItem == StatusItem.SELECT) StatusItem.NOT_CHOICE
            else StatusItem.SELECT

        items[position.column] = items[position.column].copy(statusItem = status)

        lists[position.row] = items
        return lists
    }

    private fun startCheck(
        firstPosition: Position,
        secondPosition: Position,
        items: List<List<GameItemEngine>>,
    ): Boolean {
        return locationStatus(firstPosition, secondPosition, items) != LocationStatus.PASS
    }

    private fun getSelectPositions(position: Position): List<Position> {
        positionList.add(position)
        return if (positionList.size == 2) {
            val isSorted = positionList.first().row == positionList.last().row
            positionList.sortBy { if (isSorted) it.column else it.row }
            val firstPosition = positionList.first()
            val lastPosition = positionList.last()
            positionList.clear()
            listOf(firstPosition, lastPosition)
        } else emptyList()
    }

    fun choiceItems(
        position: Position,
        list: List<List<GameItemEngine>>,
    ): List<List<GameItemEngine>> {

        val listPositions = getSelectPositions(position)
        val newItems = if (listPositions.isNotEmpty()) {
            val isChoiceItems = startCheck(listPositions.first(), listPositions.last(), list)
            list.reWriteItems(listPositions, isChoiceItems)
        } else list
        return newItems
    }

    fun helpButton(items: List<GameItemEngine>): Pair<Int, Int> {
        helpPosition = helper.getHelpPosition(items)
        return helpPosition
    }

    fun deleteInLine(
        firstPosition: Int,
        secondPosition: Int,
        items: List<GameItemEngine>,
    ): List<Int> {
        return deleteLine.delete(firstPosition / 9, secondPosition / 9, items)
    }


    fun addList(items: List<List<GameItemEngine>>): List<List<GameItemEngine>> {
        val newItems = mutableListOf<GameItemEngine>()
        val oldItems = mutableListOf<GameItemEngine>()
        val newList = mutableListOf<List<GameItemEngine>>()
        var counter = 0

        items.forEach { list ->
            list.forEach { gameItem ->
                oldItems.add(gameItem)
                if (gameItem.statusItem == StatusItem.HELP || gameItem.statusItem == StatusItem.SELECT)
                    gameItem.statusItem = StatusItem.NOT_CHOICE

                if (gameItem.statusItem == StatusItem.NOT_CHOICE)
                    newItems.add(GameItemEngine(gameItem.number, gameItem.statusItem))
            }
        }
        oldItems.addAll(newItems)

        while (oldItems.size > counter) {
            val listItems = mutableListOf<GameItemEngine>()
            for (column in 0 until 9) {
                if (oldItems.size > counter) listItems.add(oldItems[counter])
                counter++
            }
            newList.add(listItems)
        }

        return newList
    }

    fun isWin(items: List<GameItemEngine>): Boolean {
        var counter = 0
        return if (items.size < 9) {
            items.forEach { gameItem ->
                if (gameItem.statusItem == StatusItem.CHOICE) counter++
                else return@forEach
            }
            Log.d("Kart", "${counter == items.size}")
            counter == items.size
        } else false
    }
}
