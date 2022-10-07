package com.example.nineteen_2_0.data.itemlist

import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.StatusItem
import com.example.nineteen_2_0.data.gameitem.StatusItem.*
import kotlin.random.Random
import kotlin.random.nextInt

abstract class CreateItemList {

    abstract fun create(): MutableList<GameItem>

    val baseItem = mutableListOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 1, 1, 2, 1,
        3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9
    )
}