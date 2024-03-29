package ru.sr.nineteen.engin.changeitems

import ru.sr.nineteen.engin.LocationIdentifier
import ru.sr.nineteen.engin.reWriteItemsChoiceOrNotChoice
import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.LocationStatus
import ru.sr.nineteen.gameitem.Position
import ru.sr.nineteen.gameitem.StatusItem

internal object FieldChooser : LocationIdentifier() {

    private val positionList = mutableListOf<Position>()

    private var firstPosition: Position? = null
    private var secondPosition: Position? = null

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

    fun choiceItems(
        position: Position,
        list: List<List<GameItemEngine>>,
    ): List<List<GameItemEngine>>? {

        val listPositions = getSelectPositions(position)
        val newItems = if (listPositions.isNotEmpty()) {
            val isChoiceItems = startCheck(listPositions.first(), listPositions.last(), list)
            list.reWriteItemsChoiceOrNotChoice(listPositions, isChoiceItems)
        } else null
        return newItems
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
            firstPosition = positionList.first()
            secondPosition = positionList.last()
            positionList.clear()
            listOf(firstPosition!!, secondPosition!!)
        } else emptyList()
    }

    fun deleteItems(
        items: List<List<GameItemEngine>>,
    ): List<List<GameItemEngine>> {
        val newItems = items.toMutableList()
        if (firstPosition != null && secondPosition != null) {
            val firstListSize = newItems[firstPosition!!.row].size
            val secondListSize = newItems[secondPosition!!.row].size
            var deleteCounterFirst = 0
            var deleteCounterSecond = 0

            newItems[firstPosition!!.row].forEach { item ->
                if (item.statusItem == StatusItem.CHOICE) deleteCounterFirst++
            }
            newItems[secondPosition!!.row].forEach { item ->
                if (item.statusItem == StatusItem.CHOICE) deleteCounterSecond++
            }
            val deleteList = mutableListOf<List<GameItemEngine>>()
            if (firstListSize == deleteCounterFirst) deleteList.add(newItems[firstPosition!!.row])
            if (secondListSize == deleteCounterSecond) deleteList.add(newItems[secondPosition!!.row])
            if (deleteList.isNotEmpty()) newItems.removeAll(deleteList)
            secondPosition = null
            firstPosition = null
        }
        return newItems
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
                    newItems.add(
                        GameItemEngine(
                            gameItem.number,
                            gameItem.statusItem
                        )
                    )
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
}