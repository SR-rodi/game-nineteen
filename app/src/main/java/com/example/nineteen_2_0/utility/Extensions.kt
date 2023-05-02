package com.example.nineteen_2_0.utility

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.nineteen_2_0.data.database.entity.GameListEntity
import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.SettingGame
import com.example.nineteen_2_0.data.gameitem.StatusItem
import com.example.nineteen_2_0.presentation.adapter.fieldadapter.GameAdapter

fun List<GameItem>.isChoice(first: Int, second: Int) {
    this[first].statusItem = StatusItem.CHOICE
    this[second].statusItem = StatusItem.CHOICE
}

fun List<GameItem>.isHelp(pair: Pair<Int, Int>) {
    if (pair.first != pair.second) {
        this[pair.first].statusItem = StatusItem.HELP
        this[pair.second].statusItem = StatusItem.HELP
    }
}

fun List<GameItem>.isNotChoice(first: Int, second: Int? = null) {
    this[first].statusItem = StatusItem.NOT_CHOICE
    if (second != null)
        this[second].statusItem = StatusItem.NOT_CHOICE
}

fun GameItem.isSelect() {
    this.statusItem = StatusItem.SELECT
}

fun GameAdapter.notifyTwoPosition(first: Int, second: Int) {
    this.notifyItemChanged(first)
    this.notifyItemChanged(second)
}

fun GameAdapter.notifyLineRemove(position: List<Int>) {
    position.forEachIndexed {index,it->
        if (index!=position.lastIndex && index != position.lastIndex-1)
            notifyItemRemoved(it)
    }
}

fun List<GameItem>.checkNumberAndStatus(
    first: Int,
    second: Int,
    itemList: MutableList<GameItem>
): Boolean {
    val a = (itemList[first].number == itemList[second].number
            || itemList[first].number + itemList[second].number == 10)

    val b = (itemList[first].statusItem != StatusItem.CHOICE
            && itemList[second].statusItem != StatusItem.CHOICE)

    return a && b
}

fun GameListEntity.toSettingGame() = SettingGame(gameMode,list,time,stepCount)

fun View.setClickFromNavigate(action: NavDirections){
    when (this) {
        is CustomButtonView -> this.setListener { findNavController().navigate(action) }
        else -> this.setOnClickListener { findNavController().navigate(action) }
    }
}
