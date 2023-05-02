package ru.sr.nineteen.data.itemlist

import ru.sr.nineteen.data.gameitem.GameItem
import ru.sr.nineteen.data.gameitem.StatusItem
import kotlin.random.Random
import kotlin.random.nextInt

class TestList : CreateItemList() {
    override fun create(): MutableList<GameItem> {

        val itemList = mutableListOf(GameItem(5, StatusItem.NOT_CHOICE))

        repeat(13) {
            itemList.add(GameItem(Random.nextInt(1..9), StatusItem.CHOICE))
        }
        itemList.add(GameItem(5, StatusItem.NOT_CHOICE))
        repeat(3) {
            itemList.add(GameItem(Random.nextInt(1..9), StatusItem.NOT_VISIBLE))
        }
        return itemList

    }
}