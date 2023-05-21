package ru.sr.nineteen.presentation.field.viewmodel.model

import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.gameitem.Position
import ru.sr.nineteen.gameitem.SettingGame

sealed interface GameEvent{
    object OnClickBackArrow : GameEvent
    object OnResetActions : GameEvent
    class OnStartGame(val gameMode: GameMode.Game): GameEvent
    class OnClickItem(val position: Position) : GameEvent
    object OnClickAddButton : GameEvent
    object OnClickHelpButton : GameEvent
    object OnDispose : GameEvent
    object OnWinOpen : GameEvent
    object OnClickMenuWithDialog : GameEvent
    object OnClickRatingWithDialog : GameEvent
}