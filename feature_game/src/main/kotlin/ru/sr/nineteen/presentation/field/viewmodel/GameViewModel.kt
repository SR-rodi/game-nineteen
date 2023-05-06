package ru.sr.nineteen.presentation.field.viewmodel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import ru.sr.mimeteen.database.entity.GameListEntity
import ru.sr.mimeteen.database.repository.GameRepository
import ru.sr.mimeteen.database.repository.RatingRepository
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.data.database.entity.RatingEntity
import ru.sr.nineteen.domain.gameitem.Position
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.domain.logic.ClassicGameLogic
import ru.sr.nineteen.presentation.field.viewmodel.model.GameAction
import ru.sr.nineteen.presentation.field.viewmodel.model.GameEvent
import ru.sr.nineteen.presentation.field.viewmodel.model.GameState

class GameViewModel(
    private val gameRepository: GameRepository,
    private val ratingRepository: RatingRepository,
) : BaseViewModel<GameState, GameAction, GameEvent>(GameState()) {

    private val game by lazy { ClassicGameLogic() }

    override fun obtainEvent(viewEvent: GameEvent) {
        when (viewEvent) {

            is GameEvent.OnStartGame -> startSetting(viewEvent.settingGame)
            is GameEvent.OnClickItem -> playGame(viewEvent.position)
            GameEvent.OnClickAddButton -> addList()
            GameEvent.OnResetActions -> onResetAction()
            GameEvent.OnClickBackArrow -> onBackStack()
            GameEvent.OnClickHelpButton -> onGetHelpItem()
            GameEvent.OnWinOpen -> onSaveAndOpenWinScreen()
            GameEvent.OnDispose -> onDispose()

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
    }

    private fun playGame(position: Position) = scopeLaunch {
        viewState = viewState.copy(items = game.selectItems(viewState.items, position))
        delay(100)
        viewState = viewState.copy(items = game.choiceItems(position, viewState.items))
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
            ratingRepository.insertNewRating(
                RatingEntity(
                    viewState.mode, viewState.timeCounter, viewState.stepCounter
                )
            )
            gameRepository.deleteItemList()
        }
    }

    private fun onDispose() {
        scopeLaunch(context = Dispatchers.IO) {
            if (viewState.items.isNotEmpty())
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

}