package ru.sr.nineteen.itemlist

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.data.gameitem.StatusItem

class TrainingItemList: CreateItemList(){
    override fun create(): MutableList<GameItem> {
        val itemList = mutableListOf<GameItem>()
        baseItem.forEach{ _->
            itemList.add(GameItem(9, StatusItem.CHOICE))
        }
        return itemList
    }
}