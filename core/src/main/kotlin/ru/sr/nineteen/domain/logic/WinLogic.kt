package ru.sr.nineteen.domain.logic

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.StatusItem

class WinLogic(private val itemList: List<GameItem>) {

    fun checkWin(): Boolean {
        var counter = 0
        if (itemList.size < 9) {
            itemList.forEach { gameItem ->
                if (gameItem.statusItem == StatusItem.CHOICE) counter++
                else return@forEach
            }
            return counter == itemList.size
        } else return false
    }
}