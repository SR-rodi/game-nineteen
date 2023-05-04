package ru.sr.nineteen.presentation.viewmodel.model

import ru.sr.nineteen.domain.gameitem.GameItem

data class GameState (
   val isStartTamer:Boolean = true,
   val isWin:Boolean = false,
   val items:List<GameItem> = listOf(),
   val timeCounter:Int = 0,
   val stepCounter:Int = 1,
   val mode:String = "classic"
)
