package ru.sr.nineteen.itemlist

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.StatusItem
import kotlin.random.Random
import kotlin.random.nextInt

class RandomItemList: CreateItemList() {
    override fun create(): MutableList<GameItem> {
            val item = baseItem
            val itemList = mutableListOf<GameItem>()
            for (i in 0 until baseItem.size){
                val position = Random.nextInt(0..item.lastIndex)
                itemList.add(GameItem(item[position], StatusItem.NOT_CHOICE))
                item.removeAt(position)
            }
            return itemList

    }
}