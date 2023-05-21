package ru.sr.nineteen.engin.changeitems

import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.gameitem.StatusItem
import kotlin.random.Random
import kotlin.random.nextInt

internal object GameFieldCreator {

    private const val COLUMN_SIZE = 9

    private val baseItem = mutableListOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 1, 1, 2, 1,
        3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9
    )


    fun create(mode: GameMode): List<List<GameItemEngine>> {
        return when (mode) {
            GameMode.Game.Classic -> createClassicGame()
            GameMode.Game.Random -> createRandomGame()
            GameMode.Game.Next -> emptyList()
            GameMode.Training.First -> createStartTrainingList()
            GameMode.Training.Second -> createSecondTrainingList()
            GameMode.Training.Third -> createThirdTrainingList()
            GameMode.Training.Fourth -> createFourthTrainingList()
            GameMode.Training.Fifth -> createFifthTrainingList()
        }
    }

    private fun createClassicGame(): List<List<GameItemEngine>> {

        val items = mutableListOf<MutableList<GameItemEngine>>()
        var numberIndex = 0
        for (row in 1..3) {
            val childItems = mutableListOf<GameItemEngine>()
            for (column in 1..COLUMN_SIZE) {
                if (baseItem.size > numberIndex)
                    childItems.add(
                        GameItemEngine(
                            baseItem[numberIndex],
                            StatusItem.NOT_CHOICE
                        )
                    )
                numberIndex++
            }
            if (childItems.isNotEmpty()) items.add(childItems)
        }
        return items
    }

    private fun createRandomGame(): List<List<GameItemEngine>> {
        val newBaseItems = baseItem.toMutableList()
        val items = mutableListOf<MutableList<GameItemEngine>>()
        for (row in 1..3) {
            val childItems = mutableListOf<GameItemEngine>()
            for (column in 1..COLUMN_SIZE) {
                val position = Random.nextInt(0..newBaseItems.lastIndex)
                if (baseItem.size > position) {
                    childItems.add(
                        GameItemEngine(baseItem[position], StatusItem.NOT_CHOICE)
                    )
                    newBaseItems.removeAt(position)
                }
            }
            if (childItems.isNotEmpty()) items.add(childItems)
        }
        return items
    }


    private fun createStartTrainingList(): List<List<GameItemEngine>> {
        val itemList = createChoiceItems()
        itemList[1][1] = GameItemEngine(8, StatusItem.NOT_CHOICE)
        itemList[1][2] = GameItemEngine(8, StatusItem.NOT_CHOICE)
        itemList[0][7] = GameItemEngine(8, StatusItem.NOT_CHOICE)
        itemList[1][7] = GameItemEngine(8, StatusItem.NOT_CHOICE)

        return itemList
    }

    private fun createSecondTrainingList(): List<List<GameItemEngine>> {
        val itemList = createChoiceItems()
        itemList[1][1] = GameItemEngine(4, StatusItem.NOT_CHOICE)
        itemList[1][2] = GameItemEngine(6, StatusItem.NOT_CHOICE)
        itemList[0][7] = GameItemEngine(1, StatusItem.NOT_CHOICE)
        itemList[1][7] = GameItemEngine(9, StatusItem.NOT_CHOICE)
        return itemList
    }

    private fun createThirdTrainingList(): List<List<GameItemEngine>> {
        val itemList = createChoiceItems()
        itemList[1][1] = GameItemEngine(4, StatusItem.NOT_CHOICE)
        itemList[1][8] = GameItemEngine(6, StatusItem.NOT_CHOICE)
        itemList[0][7] = GameItemEngine(1, StatusItem.NOT_CHOICE)
        itemList[2][7] = GameItemEngine(1, StatusItem.NOT_CHOICE)
        return itemList
    }

    private fun createFourthTrainingList(): List<List<GameItemEngine>> {
        val itemList = createChoiceItems()
        itemList[0][4] = GameItemEngine(7, StatusItem.NOT_CHOICE)
        itemList[1][2] = GameItemEngine(3, StatusItem.NOT_CHOICE)

        return itemList
    }

    private fun createFifthTrainingList(): List<List<GameItemEngine>> {
        val itemList = createChoiceItems()
        itemList[0][4] = GameItemEngine(7, StatusItem.HELP)
        itemList[1][2] = GameItemEngine(3, StatusItem.HELP)

        return itemList
    }

    private fun createChoiceItems(): MutableList<MutableList<GameItemEngine>> {
        val items = mutableListOf<MutableList<GameItemEngine>>()
        for (row in 1..3) {
            val childItems = mutableListOf<GameItemEngine>()
            for (column in 1..9)
                childItems.add(GameItemEngine(9, StatusItem.CHOICE))
            if (childItems.isNotEmpty()) items.add(childItems)
        }
        return items
    }
}