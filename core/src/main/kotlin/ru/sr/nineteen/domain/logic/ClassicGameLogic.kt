package ru.sr.nineteen.domain.logic

import android.util.Log
import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.domain.gameitem.LocationStatus
import ru.sr.nineteen.utility.reWriteItems


class ClassicGameLogic : BaseLogic() {

    private val helper: Helper = Helper()
    private val deleteLine = DeleteLine()
    private var helpPosition = Pair(0, 0)
    private val positionList = mutableListOf<Pair<Int, Int>>()

    fun selectItems(
        list: List<List<GameItemEngine>>,
        position: Pair<Int, Int>,
    ): List<List<GameItemEngine>> {
        val lists = list.toMutableList()
        val items = lists[position.first].toMutableList()
        val status =
            if (items[position.second].statusItem == StatusItem.SELECT) StatusItem.NOT_CHOICE
            else StatusItem.SELECT

        items[position.second] = items[position.second].copy(statusItem = status)

        lists[position.first] = items
        return lists
    }

    private fun startCheck(
        firstPosition: Pair<Int, Int>,
        secondPosition: Pair<Int, Int>,
        items: List<List<GameItemEngine>>,
    ): Boolean {
        return locationStatus(firstPosition, secondPosition, items) != LocationStatus.PASS
    }

    private fun getSelectPositions(position: Pair<Int, Int>): List<Pair<Int, Int>> {
        positionList.add(position)
        return if (positionList.size == 2) {
            val isSorted = positionList.first().first == positionList.last().first
            positionList.sortBy { if (isSorted) it.second else it.first }
            val firstPosition = positionList.first()
            val lastPosition = positionList.last()
            positionList.clear()
            listOf(firstPosition, lastPosition)
        } else emptyList()
    }

    fun choiceItems(
        position: Pair<Int, Int>,
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


    fun addList(items: List<GameItemEngine>): List<GameItemEngine> {
        val newItems = mutableListOf<GameItemEngine>()
        items.forEach { gameItem ->
            if (gameItem.statusItem == StatusItem.HELP || gameItem.statusItem == StatusItem.SELECT)
                gameItem.statusItem = StatusItem.NOT_CHOICE

            if (gameItem.statusItem == StatusItem.NOT_CHOICE)
                newItems.add(GameItemEngine(gameItem.number, gameItem.statusItem))
        }

        val newList = items.toMutableList()
        newList.addAll(newItems)
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
