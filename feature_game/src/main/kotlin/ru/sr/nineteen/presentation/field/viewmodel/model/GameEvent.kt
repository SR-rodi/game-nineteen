package ru.sr.nineteen.presentation.field.viewmodel.model

import ru.sr.nineteen.domain.gameitem.Position
import ru.sr.nineteen.domain.gameitem.SettingGame

sealed interface GameEvent{
    object OnClickBackArrow : GameEvent
    object ResetActions : GameEvent
    class OnStartGame(val settingGame: SettingGame): GameEvent
    class OnClickItem(val position: Position) : GameEvent
    object OnClickAddButton : GameEvent
    object OnClickHelpButton : GameEvent
    object OnDispose : GameEvent
    object OnWinOpen : GameEvent
}