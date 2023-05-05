package ru.sr.nineteen.presentation.viewmodel

import kotlinx.coroutines.delay
import ru.sr.mimeteen.database.repository.GameRepository
import ru.sr.mimeteen.database.repository.RatingRepository
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.gameitem.Position
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.domain.logic.ClassicGameLogic
import ru.sr.nineteen.presentation.viewmodel.model.GameAction
import ru.sr.nineteen.presentation.viewmodel.model.GameEvent
import ru.sr.nineteen.presentation.viewmodel.model.GameState

class GameViewModel(
    private val gameRepository: GameRepository,
    private val ratingRepository: RatingRepository,
) : BaseViewModel<GameState, GameAction, GameEvent>(GameState()) {

    private val game by lazy { ClassicGameLogic() }

    override fun obtainEvent(viewEvent: GameEvent) {
        when (viewEvent) {
            is GameEvent.OnStartGame -> startSetting(viewEvent.settingGame)
            is GameEvent.OnClickItem -> playGame(viewEvent.position)
            GameEvent.OnClickAddButton-> addList()
            else -> {}
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
        /*   startTimer()*/
    }

    private fun playGame(position: Position) =scopeLaunch {
            viewState = viewState.copy(items = game.selectItems(viewState.items, position))
            delay(100)
            viewState = viewState.copy(items = game.choiceItems(position, viewState.items))
        }

    private fun addList() {
        viewState = viewState.copy(items = game.addList(viewState.items))
    }

}