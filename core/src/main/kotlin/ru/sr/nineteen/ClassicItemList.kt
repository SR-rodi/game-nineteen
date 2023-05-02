package ru.sr.nineteen

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.itemlist.CreateItemList

class ClassicItemList: CreateItemList(){

    override fun create(): MutableList<GameItem> {
        val itemList = mutableListOf<GameItem>()
        baseItem.forEach{ number->
            itemList.add(GameItem(number, StatusItem.NOT_CHOICE))
        }
        return itemList
    }
}

