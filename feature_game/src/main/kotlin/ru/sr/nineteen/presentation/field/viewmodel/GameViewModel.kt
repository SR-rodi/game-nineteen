package ru.sr.nineteen.presentation.field.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.sr.mimeteen.database.entity.GameListEntity
import ru.sr.mimeteen.database.repository.FieldDBRepository
import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.model.GameRating
import ru.sr.nineteen.domain.reposytory.RemoteRatingRepository
import ru.sr.nineteen.engin.GameEngin
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.presentation.field.viewmodel.model.GameAction
import ru.sr.nineteen.presentation.field.viewmodel.model.GameEvent
import ru.sr.nineteen.presentation.field.viewmodel.model.GameState
import ru.sr.nineteen.gameitem.Position
import ru.sr.nineteen.gameitem.SettingGame

class GameViewModel(
    private val fieldDBRepository: FieldDBRepository,
    private val game: GameEngin,
    private val remoteRating: RemoteRatingRepository,
) : BaseViewModel<GameState, GameAction, GameEvent>(GameState()) {

    private var userRemoteBestResult: Long? = null

    override fun obtainEvent(viewEvent: GameEvent) {
        when (viewEvent) {

            is GameEvent.OnStartGame -> startSetting(viewEvent.gameMode)
            is GameEvent.OnClickItem -> playGame(viewEvent.position)
            GameEvent.OnClickAddButton -> addList()
            GameEvent.OnResetActions -> onResetAction()
            GameEvent.OnClickBackArrow -> onBackStack()
            GameEvent.OnClickHelpButton -> onGetHelpItem()
            GameEvent.OnWinOpen -> onSaveAndOpenWinScreen()
            GameEvent.OnDispose -> onDispose()
            GameEvent.OnClickMenuWithDialog -> viewAction = GameAction.GoToBackStack
            GameEvent.OnClickRatingWithDialog -> viewAction = GameAction.OpenRating
        }
    }

    private fun startSetting(mode: GameMode.Game) {
        scopeLaunch {
            userRemoteBestResult = remoteRating.getCurrentRating()?.time
        }
        startTimer()
        viewState = viewState.copy(isStartTamer = true)

        viewState = viewState.copy(
            items = game.createGameFieldByGameMode(mode),
            timeCounter = 0,
            stepCounter = 0,
            mode = mode
        )
    }

    private fun playGame(position: Position) = scopeLaunch {
        viewState = viewState.copy(items = game.selectItem(viewState.items, position))
        delay(100)
        val newItems = game.choiceItems(viewState.items, position)
        val steps =
            if (newItems == null) viewState.stepCounter else viewState.stepCounter + 1
        viewState = viewState.copy(items = newItems ?: viewState.items, stepCounter = steps)
        viewState = viewState.copy(items = game.deleteItems(viewState.items))
        if (viewState.items.isEmpty()) viewAction = GameAction.SaveWinIfo
    }

    private fun addList() {
        viewState = viewState.copy(items = game.addList(viewState.items))
    }

    private fun onBackStack() {
        viewAction = GameAction.GoToBackStack
    }

    private fun onGetHelpItem() {
        viewState = viewState.copy(items = game.helpButton(viewState.items))
    }

    private fun onSaveAndOpenWinScreen() {
        scopeLaunch(context = Dispatchers.IO,
            onFinally = {
                viewAction = GameAction.OpenWinScreen(
                    SettingGame(
                        gameMode = viewState.mode,
                        list = viewState.items,
                        time = viewState.timeCounter,
                        stepCount = viewState.stepCounter
                    )
                )
            }
        ) {
            fieldDBRepository.deleteItemList()
            if (userRemoteBestResult == null || viewState.timeCounter >= userRemoteBestResult!!)
                remoteRating.setNewRating(
                    GameRating(
                        steps = viewState.stepCounter,
                        gameMode = viewState.mode.name,
                        time = viewState.timeCounter
                    )
                )
        }
    }

    private var timerCounter = 0L
    private fun startTimer() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                timerCounter++
                delay(1000)
                viewState = viewState.copy(timeCounter = timerCounter)
            }
        }
    }

    private fun onDispose() {
        scopeLaunch(context = Dispatchers.IO) {
            if (viewState.items.isNotEmpty())
                fieldDBRepository.deleteItemList()
            fieldDBRepository.insertItemList(
                GameListEntity(
                    viewState.mode,
                    viewState.items,
                    viewState.timeCounter,
                    viewState.stepCounter
                )
            )
        }
    }
}