package ru.sr.nineteen.engin

import ru.sr.nineteen.engin.changeitems.FieldChooser
import ru.sr.nineteen.engin.changeitems.GameFieldCreator
import ru.sr.nineteen.engin.changeitems.GameHelper
import ru.sr.nineteen.gameitem.GameItemEngine
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.gameitem.Position

internal class GameEnginImpl : GameEngin {

    override fun createGameFieldByGameMode(mode: GameMode): List<List<GameItemEngine>> {
        return GameFieldCreator.create(mode)
    }

    override fun selectItem(
        items: List<List<GameItemEngine>>,
        position: Position,
    ): List<List<GameItemEngine>> {
        return FieldChooser.selectItems(items, position)
    }

    override fun choiceItems(
        items: List<List<GameItemEngine>>,
        position: Position,
    ): List<List<GameItemEngine>>? {
        return FieldChooser.choiceItems(position, items)
    }

    override fun helpButton(items: List<List<GameItemEngine>>): List<List<GameItemEngine>> {
        return GameHelper.helpButton(items)
    }

    override fun deleteItems(items: List<List<GameItemEngine>>): List<List<GameItemEngine>> {
        return FieldChooser.deleteItems(items)
    }

    override fun addList(items: List<List<GameItemEngine>>): List<List<GameItemEngine>> {
        return FieldChooser.addList(items)
    }

}