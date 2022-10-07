package com.example.nineteen_2_0.logic

import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.StatusItem.CHOICE

class WinLogic(private val itemList: List<GameItem>) {

    fun checkWin(): Boolean {
        var counter = 0
        if (itemList.size < 9) {
            itemList.forEach { gameItem ->
                if (gameItem.statusItem == CHOICE) counter++
                else return@forEach
            }
            return counter == itemList.size
        } else return false
    }
}