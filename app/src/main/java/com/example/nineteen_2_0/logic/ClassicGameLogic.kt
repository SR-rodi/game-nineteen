package com.example.nineteen_2_0.logic


import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.StatusItem.*
import com.example.nineteen_2_0.utility.isChoice
import com.example.nineteen_2_0.utility.isHelp
import com.example.nineteen_2_0.utility.isNotChoice
import com.example.nineteen_2_0.utility.isSelect

class ClassicGameLogic(
    private val itemList: MutableList<GameItem>
) : BaseLogic(itemList) {

    private val helper: Helper = Helper(itemList)
    private val deleteLine = DeleteLine(itemList)
    private var helpPosition = Pair(0,0)

    fun helpButton(): Pair<Int, Int> {
        helpPosition = helper.testHelper()
        itemList.isHelp(helpPosition)
        return helpPosition
    }

    fun start(first: Int, second: Int): List<Int> {
        return when (locationStatus(first, second)) {
            LocationStatus.PASS -> {
                itemList.isNotChoice(first, second)
                if (helpPosition.first != helpPosition.second){
                    itemList.isNotChoice(helpPosition.first, helpPosition.second)
                    helpPosition = Pair(0,0)
                }
                emptyList()
            }
            else -> {
                if (helpPosition.first != helpPosition.second){
                    itemList.isNotChoice(helpPosition.first, helpPosition.second)
                    helpPosition = Pair(0,0)
                }
                itemList.isChoice(first, second)

                deleteLine.delete(first / 9, second / 9)
            }
        }
    }

    fun selectItem(position: Int) =
        when (itemList[position].statusItem) {
            SELECT -> itemList.isNotChoice(position)
            else -> itemList[position].isSelect()
        }

    fun addList(): Pair<Int, Int> {
        val newList = mutableListOf<GameItem>()
        val a = itemList.lastIndex

        itemList.forEach { gameItem ->
            if (gameItem.statusItem == HELP || gameItem.statusItem == SELECT)
                gameItem.statusItem = NOT_CHOICE

            if (gameItem.statusItem == NOT_CHOICE)
                newList.add(GameItem(gameItem.number, gameItem.statusItem))
        }
        itemList.addAll(newList)
        return Pair(a, newList.size)
    }
}