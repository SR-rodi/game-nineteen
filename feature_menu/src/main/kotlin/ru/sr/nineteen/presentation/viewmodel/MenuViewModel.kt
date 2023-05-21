package ru.sr.nineteen.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus
import ru.sr.mimeteen.database.repository.FieldDBRepository
import ru.sr.mimeteen.remotedatabase.UserProvider
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.presentation.viewmodel.model.MenuAction
import ru.sr.nineteen.presentation.viewmodel.model.MenuEvent
import ru.sr.nineteen.presentation.viewmodel.model.MenuState

class MenuViewModel(
    private val gameRepository: FieldDBRepository,
    private val userProvider: UserProvider,
) : BaseViewModel<MenuState, MenuAction, MenuEvent>(MenuState()) {

    private var localSettings: ru.sr.nineteen.gameitem.SettingGame? = null

    init {
        getGameList()
    }

    override fun obtainEvent(viewEvent: MenuEvent) {
        when (viewEvent) {
            MenuEvent.OnClickClassicButton -> onStartGame(GameMode.Game.Classic)
            MenuEvent.OnClickNextButton -> localSettings?.let { onStartGame(GameMode.Game.Next) }
            MenuEvent.OnClickRandomButton -> onStartGame(GameMode.Game.Random)
            MenuEvent.OnClickRatingButton -> onStartRating()
            MenuEvent.OnClickTrainingButton -> onStartTraining()
            MenuEvent.ResetActions -> onResetAction()
            MenuEvent.OnClickProfileButton -> onOpenProfile()
        }
    }

    private fun onOpenProfile() {
        viewAction = MenuAction.OpenProfile
    }

    private fun onStartTraining() {
        viewAction = MenuAction.OpenTraining
    }

    private fun onStartRating() {
        viewAction = MenuAction.OpenRating
    }

    private fun onStartGame(game: GameMode) {
        viewAction = MenuAction.OpenGame(game)
    }


    private fun getGameList() {
        viewState = viewState.copy(userAvatar = userProvider.getUser().photoUri)
        gameRepository.getGameList().onEach { entity ->
            if (entity != null) {
                viewState = if (entity.list.isNotEmpty()) {
                    localSettings = entity.toSettingGame()
                    viewState.copy(isNextEnable = true)
                } else viewState.copy(isNextEnable = true)
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }
}