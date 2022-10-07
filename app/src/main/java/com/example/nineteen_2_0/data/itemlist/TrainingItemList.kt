package com.example.nineteen_2_0.data.itemlist

import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.StatusItem

class TrainingItemList: CreateItemList(){
    override fun create(): MutableList<GameItem> {
        val itemList = mutableListOf<GameItem>()
        baseItem.forEach{ _->
            itemList.add(GameItem(9, StatusItem.CHOICE))
        }
        return itemList
    }
}