package ru.sr.nineteen.engin

import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.LocationStatus
import ru.sr.nineteen.gameitem.Position
import ru.sr.nineteen.gameitem.StatusItem

internal abstract class LocationIdentifier {

    protected fun locationStatus(
        firstPosition: Position,
        secondPosition: Position,
        items: List<List<GameItemEngine>>,
    ): LocationStatus {
        return if (items.checkNumberAndStatus(firstPosition, secondPosition))

            when {
                checkNear(firstPosition, secondPosition) -> LocationStatus.NEAR

                checkVertical(firstPosition, secondPosition, items) ->
                    LocationStatus.VERTICAL

                checkHorizontalItem(firstPosition, secondPosition, items) ->
                    LocationStatus.HORIZONTAL

                else -> LocationStatus.PASS
            }
        else LocationStatus.PASS
    }

    private fun checkNear(firstPosition: Position, secondPosition: Position): Boolean {
        return (firstPosition.row + 1 == secondPosition.row && firstPosition.column == secondPosition.column)
                || (firstPosition.row == secondPosition.row && firstPosition.column + 1 == secondPosition.column)
    }

    private fun checkVertical(
        firstPosition: Position,
        secondPosition: Position,
        items: List<List<GameItemEngine>>,
    ): Boolean {
        if (firstPosition.row == secondPosition.row) return false
        if (firstPosition.column != secondPosition.column) return false
        val listItem = mutableListOf<GameItemEngine>()
        val expectedSize = secondPosition.row - firstPosition.row - 1
        for (row in firstPosition.row + 1 until secondPosition.row) {
            val item = items[row][firstPosition.column]
            if (item.statusItem == StatusItem.CHOICE) listItem.add(item)
        }
        return listItem.size == expectedSize
    }

    private fun checkHorizontalItem(
        firstPosition: Position,
        secondPosition: Position,
        items: List<List<GameItemEngine>>,
        columns: Int = 9,
    ): Boolean {

        val startIndex = firstPosition.row * columns + firstPosition.column
        val finishIndex = secondPosition.row * columns + secondPosition.column
        val expectedSize = finishIndex - startIndex - 1
        val listItem = mutableListOf<GameItemEngine>()

        for (row in firstPosition.row..secondPosition.row) {
            val startColumn = if (row == firstPosition.row) firstPosition.column + 1 else 0
            val finishColumn = if (row == secondPosition.row) secondPosition.column else columns
            for (column in startColumn until finishColumn) {
                val item = items[row][column]
                if (item.statusItem == StatusItem.CHOICE) listItem.add(item)
            }
        }

        return listItem.size == expectedSize
    }
}
