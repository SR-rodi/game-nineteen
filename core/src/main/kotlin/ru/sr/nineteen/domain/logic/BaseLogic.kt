package ru.sr.nineteen.domain.logic


import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.domain.gameitem.LocationStatus
import ru.sr.nineteen.domain.gameitem.Position
import ru.sr.nineteen.utility.checkNumberAndStatus
import java.net.PasswordAuthentication
import kotlin.math.abs

abstract class BaseLogic {

    protected fun locationStatus(
        firstPosition: Position,
        secondPosition: Position,
        items: List<List<GameItemEngine>>,
    ): LocationStatus {

        return if (items.checkNumberAndStatus(firstPosition, secondPosition))
            checkPosition(firstPosition, secondPosition)
        else LocationStatus.PASS
    }
}

private fun checkPosition(
    firstPosition: Position,
    secondPosition: Position,
): LocationStatus {


    return when {
        firstPosition.column == secondPosition.column
                && firstPosition.row == secondPosition.row -> LocationStatus.PASS
        firstPosition.row == secondPosition.row -> LocationStatus.HORIZONTAL
        firstPosition.column == secondPosition.column -> LocationStatus.VERTICAL
        else -> LocationStatus.PASS
    }
}


/*        when {
            checkNear(first, second) == LocationStatus.NEAR -> LocationStatus.NEAR
            checkHorizontal(
                first,
                second,
                items
            ) == LocationStatus.HORIZONTAL -> LocationStatus.HORIZONTAL

            checkVertical(
                first,
                second,
                items
            ) == LocationStatus.VERTICAL -> LocationStatus.VERTICAL

            else -> LocationStatus.PASS

        }*/

/*   if (checkNear(first, second) == LocationStatus.NEAR) LocationStatus.NEAR
   else if (checkHorizontal(first, second, items) == LocationStatus.HORIZONTAL) LocationStatus.HORIZONTAL
   else if (checkVertical(first, second, items) == LocationStatus.VERTICAL) LocationStatus.VERTICAL
   else LocationStatus.PASS*/

private fun checkNear(first: Int, second: Int) =
    when (first + 1 == second || first + 9 == second) {
        true -> LocationStatus.NEAR
        false -> LocationStatus.PASS
    }

private fun checkHorizontal(
    first: Int,
    second: Int,
    items: List<GameItemEngine>,
): LocationStatus {
    var counter = 0
    for (i in first + 1 until second) {
        if (items[i].statusItem == StatusItem.CHOICE) counter++
        else break
    }
    return when (abs(first - second) - 1 == counter) {
        true -> LocationStatus.HORIZONTAL
        false -> LocationStatus.PASS
    }
}

private fun checkVertical(
    first: Int,
    second: Int,
    items: List<GameItemEngine>,
): LocationStatus {
    var counter = 0
    if ((second - first) % 9 == 0)
        for (i in 1..second / 9 - first / 9) {
            if (items[first + (9 * i)].statusItem == StatusItem.CHOICE) counter++
            else break
        }
    return when (counter > 0 && counter == (second / 9 - first / 9) - 1) {
        true -> LocationStatus.VERTICAL
        false -> LocationStatus.PASS
    }
}
