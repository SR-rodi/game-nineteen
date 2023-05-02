package ru.sr.nineteen.data.itemlist

import ru.sr.nineteen.data.gameitem.GameItem
import ru.sr.nineteen.data.gameitem.StatusItem

class ClassicItemList: CreateItemList(){

    override fun create(): MutableList<GameItem> {
        val itemList = mutableListOf<GameItem>()
        baseItem.forEach{ number->
            itemList.add(GameItem(number, StatusItem.NOT_CHOICE))
        }
        return itemList
    }
}

