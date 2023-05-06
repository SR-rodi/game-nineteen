/*
package ru.sr.nineteen.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.sr.mimeteen.database.repository.GameRepository
import ru.sr.mimeteen.database.repository.RatingRepository
import ru.sr.nineteen.BaseViewModel
import ru.sr.mimeteen.database.entity.GameListEntity
import ru.sr.nineteen.data.database.entity.RatingEntity
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.domain.logic.ClassicGameLogic
import ru.sr.nineteen.presentation.viewmodel.model.GameAction
import ru.sr.nineteen.presentation.viewmodel.model.GameEvent
import ru.sr.nineteen.presentation.viewmodel.model.GameState

class GameViewModel(
    private val gameRepository: GameRepository,
    private val ratingRepository: RatingRepository,
) : BaseViewModel<GameState, GameAction, GameEvent>(GameState()) {

    override fun obtainEvent(viewEvent: GameEvent) {
        when (viewEvent) {
            is GameEvent.OnStartGame -> {
                startSetting(viewEvent.settingGame)

            }

            is GameEvent.OnClickItem -> playGame(viewEvent.position)
            GameEvent.OnClickBackArrow -> onBackStack()
            GameEvent.OnResetActions -> onResetAction()
            GameEvent.OnClickAddButton -> addList()
            GameEvent.OnClickHelpButton -> help()
            GameEvent.OnDispose -> {
                addGameDatabase()
                viewState = viewState.copy(isStartTamer = false)
            }

            GameEvent.OnWinOpen -> viewAction = GameAction.OpenWinScreen
        }
    }

    private fun onBackStack() {
        viewAction = GameAction.GoToBackStack
    }

    private val game by lazy { ClassicGameLogic() }
    private val positionList = mutableListOf<Int>()

    private fun playGame(position: Int) {

        val items = viewState.items.toMutableList()
        val status = if (items[position].statusItem == StatusItem.SELECT) StatusItem.NOT_CHOICE
        else StatusItem.SELECT

        items[position] = items[position].copy(statusItem = status)
        viewState = viewState.copy(items = items)

        positionList.add(position)

        if (positionList.size == 2) {
            positionList.sort()
            val firstPosition = positionList.first()
            val lastPosition = positionList.last()
            positionList.clear()
            val isChoice = game.startCheck(firstPosition, lastPosition, viewState.items)
            if (isChoice) {
                items[firstPosition] = items[firstPosition].copy(statusItem = StatusItem.CHOICE)
                items[lastPosition] = items[lastPosition].copy(statusItem = StatusItem.CHOICE)
                viewState = viewState.copy(
                    stepCounter = viewState.stepCounter + 1,
                    isWin = true
                )
      val deleteList = game.deleteInLine(firstPosition, lastPosition, items)
                      if (deleteList.isNotEmpty()) {
                          deleteList.forEach { index -> items.removeAt(index) }
                      }

            } else {
                items[firstPosition] = items[firstPosition].copy(statusItem = StatusItem.NOT_CHOICE)
                items[lastPosition] = items[lastPosition].copy(statusItem = StatusItem.NOT_CHOICE)
            }
            viewState = viewState.copy(items = items)
        }
    }

    private fun addList() {
        viewState = viewState.copy(items = game.addList(viewState.items))

    }

    private fun help() {
        val items = viewState.items.toMutableList()
        val hepPosition = game.helpButton(viewState.items)
        items[hepPosition.first] = items[hepPosition.first].copy(statusItem = StatusItem.HELP)
        items[hepPosition.second] = items[hepPosition.second].copy(statusItem = StatusItem.HELP)
        viewState = viewState.copy(items = items)
    }

    private fun addGameDatabase() {

        scopeLaunch(context = Dispatchers.IO) {
            gameRepository.deleteItemList()
            gameRepository.insertItemList(
                GameListEntity(
                    viewState.mode,
                    viewState.items,
                    viewState.timeCounter,
                    viewState.stepCounter
                )
            )

        }
    }

    private fun startSetting(settingGame: SettingGame) {
        viewState = viewState.copy(isStartTamer = true)
                viewState = viewState.copy(
                    items = settingGame.list,
                    timeCounter = settingGame.time,
                    stepCounter = settingGame.stepCount,
                    mode = settingGame.gameMode
                )
                startTimer()
    }

    fun deleteDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.deleteItemList()
        }
    }

    private fun startTimer() {
        viewModelScope.launch(Dispatchers.IO) {
            var timer = viewState.timeCounter
            while (viewState.isStartTamer) {
                timer++
                delay(1000)
                viewState = viewState.copy(timeCounter = timer)

            }
        }
    }

    fun addRatingDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            ratingRepository.insertNewRating(
                RatingEntity(
                    viewState.mode,
                    viewState.timeCounter,
                    viewState.stepCounter
                )
            )
        }
    }
}
*/
