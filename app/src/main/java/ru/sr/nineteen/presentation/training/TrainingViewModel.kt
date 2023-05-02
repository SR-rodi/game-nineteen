package ru.sr.nineteen.presentation.training

import androidx.lifecycle.ViewModel
import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.itemlist.TrainingItemList

class TrainingViewModel : ViewModel() {

    var itemList = TrainingItemList().create()

    fun getItemListOne(): MutableList<GameItem> {
        itemList[11] = GameItem(8, StatusItem.NOT_CHOICE)
        itemList[12] = GameItem(8, StatusItem.NOT_CHOICE)
        itemList[14] = GameItem(6, StatusItem.NOT_CHOICE)
        itemList[15] = GameItem(4, StatusItem.NOT_CHOICE)
        return itemList
    }

    fun getItemListTwo(): MutableList<GameItem> {
        itemList = TrainingItemList().create()
        itemList[2] = GameItem(4, StatusItem.NOT_CHOICE)
        itemList[11] = GameItem(4, StatusItem.NOT_CHOICE)
        itemList[14] = GameItem(6, StatusItem.NOT_CHOICE)
        itemList[15] = GameItem(4, StatusItem.NOT_CHOICE)
        return itemList
    }

    fun getItemListThree(): MutableList<GameItem> {
        itemList = TrainingItemList().create()
        itemList[1] = GameItem(7, StatusItem.NOT_CHOICE)
        itemList[19] = GameItem(7, StatusItem.NOT_CHOICE)
        itemList[4] = GameItem(1, StatusItem.NOT_CHOICE)
        itemList[7] = GameItem(9, StatusItem.NOT_CHOICE)
        return itemList
    }

    fun getItemListFour(): MutableList<GameItem> {
        itemList = TrainingItemList().create()
        itemList[6] = GameItem(8, StatusItem.NOT_CHOICE)
        itemList[11] = GameItem(2, StatusItem.NOT_CHOICE)
        return itemList
    }

    fun getItemListFive(): MutableList<GameItem> {
        itemList = TrainingItemList().create()
        itemList[6] = GameItem(8, StatusItem.HELP)
        itemList[11] = GameItem(2, StatusItem.HELP)
        return itemList
    }
}
