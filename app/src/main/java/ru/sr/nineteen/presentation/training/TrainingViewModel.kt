/*
package ru.sr.nineteen.presentation.training

import androidx.lifecycle.ViewModel
import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.itemlist.TrainingItemList

class TrainingViewModel : ViewModel() {

    var itemList = TrainingItemList().create()

    fun getItemListOne(): MutableList<GameItemEngine> {
        itemList[11] = GameItemEngine(8, StatusItem.NOT_CHOICE)
        itemList[12] = GameItemEngine(8, StatusItem.NOT_CHOICE)
        itemList[14] = GameItemEngine(6, StatusItem.NOT_CHOICE)
        itemList[15] = GameItemEngine(4, StatusItem.NOT_CHOICE)
        return itemList
    }

    fun getItemListTwo(): MutableList<GameItemEngine> {
        itemList = TrainingItemList().create()
        itemList[2] = GameItemEngine(4, StatusItem.NOT_CHOICE)
        itemList[11] = GameItemEngine(4, StatusItem.NOT_CHOICE)
        itemList[14] = GameItemEngine(6, StatusItem.NOT_CHOICE)
        itemList[15] = GameItemEngine(4, StatusItem.NOT_CHOICE)
        return itemList
    }

    fun getItemListThree(): MutableList<GameItemEngine> {
        itemList = TrainingItemList().create()
        itemList[1] = GameItemEngine(7, StatusItem.NOT_CHOICE)
        itemList[19] = GameItemEngine(7, StatusItem.NOT_CHOICE)
        itemList[4] = GameItemEngine(1, StatusItem.NOT_CHOICE)
        itemList[7] = GameItemEngine(9, StatusItem.NOT_CHOICE)
        return itemList
    }

    fun getItemListFour(): MutableList<GameItemEngine> {
        itemList = TrainingItemList().create()
        itemList[6] = GameItemEngine(8, StatusItem.NOT_CHOICE)
        itemList[11] = GameItemEngine(2, StatusItem.NOT_CHOICE)
        return itemList
    }

    fun getItemListFive(): MutableList<GameItemEngine> {
        itemList = TrainingItemList().create()
        itemList[6] = GameItemEngine(8, StatusItem.HELP)
        itemList[11] = GameItemEngine(2, StatusItem.HELP)
        return itemList
    }
}
*/
