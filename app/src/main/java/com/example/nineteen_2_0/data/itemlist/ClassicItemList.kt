package com.example.nineteen_2_0.data.itemlist

import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.StatusItem

class ClassicItemList: CreateItemList(){

    override fun create(): MutableList<GameItem> {
        val itemList = mutableListOf<GameItem>()
        baseItem.forEach{ number->
            itemList.add(GameItem(number, StatusItem.NOT_CHOICE))
        }
        return itemList
    }
}

