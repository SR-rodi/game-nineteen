package ru.sr.nineteen.presentation.viewmodel.model

import ru.sr.nineteen.domain.gameitem.GameItem
import ru.sr.nineteen.domain.gameitem.SettingGame

sealed interface GameEvent{
    object OnClickBackArrow : GameEvent
    object ResetActions : GameEvent
    class OnStartGame(val settingGame: SettingGame):GameEvent
    class OnClickItem(val position: Int) : GameEvent
    object OnClickAddButton : GameEvent
    object OnClickHelpButton : GameEvent
    object OnDispose :GameEvent
    object OnWinOpen : GameEvent
}