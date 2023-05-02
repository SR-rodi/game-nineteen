package ru.sr.nineteen.domain.logic

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.domain.gameitem.LocationStatus
import ru.sr.nineteen.utility.isChoice
import ru.sr.nineteen.utility.isHelp
import ru.sr.nineteen.utility.isNotChoice
import ru.sr.nineteen.utility.isSelect


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
            StatusItem.SELECT -> itemList.isNotChoice(position)
            else -> itemList[position].isSelect()
        }

    fun addList(): Pair<Int, Int> {
        val newList = mutableListOf<GameItem>()
        val a = itemList.lastIndex

        itemList.forEach { gameItem ->
            if (gameItem.statusItem == StatusItem.HELP || gameItem.statusItem == StatusItem.SELECT)
                gameItem.statusItem = StatusItem.NOT_CHOICE

            if (gameItem.statusItem == StatusItem.NOT_CHOICE)
                newList.add(GameItem(gameItem.number, gameItem.statusItem))
        }
        itemList.addAll(newList)
        return Pair(a, newList.size)
    }
}