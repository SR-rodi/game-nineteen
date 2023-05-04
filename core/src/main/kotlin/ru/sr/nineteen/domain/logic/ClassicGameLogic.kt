package ru.sr.nineteen.domain.logic

import android.util.Log
import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.domain.gameitem.LocationStatus


class ClassicGameLogic : BaseLogic() {

    private val helper: Helper = Helper()
    private val deleteLine = DeleteLine()
    private var helpPosition = Pair(0, 0)

    fun helpButton(items: List<GameItem>): Pair<Int, Int> {
        helpPosition = helper.getHelpPosition(items)
        return helpPosition
    }

    fun deleteInLine(firstPosition:Int,secondPosition:Int,items: List<GameItem>): List<Int> {
       return deleteLine.delete(firstPosition / 9, secondPosition / 9,items)
    }

    fun startCheck(firstPosition: Int, secondPosition: Int, items: List<GameItem>): Boolean {
        return locationStatus(firstPosition, secondPosition, items) != LocationStatus.PASS
    }


    fun addList(items: List<GameItem>): List<GameItem> {
        val newItems = mutableListOf<GameItem>()
        items.forEach { gameItem ->
            if (gameItem.statusItem == StatusItem.HELP || gameItem.statusItem == StatusItem.SELECT)
                gameItem.statusItem = StatusItem.NOT_CHOICE

            if (gameItem.statusItem == StatusItem.NOT_CHOICE)
                newItems.add(GameItem(gameItem.number, gameItem.statusItem))
        }

        val newList = items.toMutableList()
        newList.addAll(newItems)
        return newList
    }

    fun isWin(items: List<GameItem>): Boolean {
        var counter = 0
        return if (items.size < 9) {
            items.forEach { gameItem ->
                if (gameItem.statusItem == StatusItem.CHOICE) counter++
                else return@forEach
            }
            Log.d("Kart","${counter == items.size}")
            counter == items.size
        } else false
    }
}