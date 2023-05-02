package ru.sr.nineteen.presentation.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.itemlist.RandomItemList
import ru.sr.nineteen.presentation.viewmodel.model.MenuAction
import ru.sr.nineteen.presentation.viewmodel.model.MenuEvent
import ru.sr.nineteen.presentation.viewmodel.model.MenuState

class MenuViewModel:BaseViewModel<MenuState,MenuAction,MenuEvent>(MenuState()) {

    private fun createRandomList(): SettingGame {
        val settingGame = SettingGame()
        settingGame.list = RandomItemList().create()
        settingGame.gameMode = "random"
        return settingGame
    }

/*    fun getGameList() {
        gameRepository.getGameList().onEach { entity ->
            if (entity != null) {
                if (entity.list.isNotEmpty()) {
                    _nextButton.value = true
                    _itemList.value = entity.toSettingGame()
                } else _nextButton.value = false
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }*/

    override fun obtainEvent(viewEvent: MenuEvent) {
        when(viewEvent){
            MenuEvent.OnClickClassicButton -> onStartGame(SettingGame())
            MenuEvent.OnClickNextButton -> onStartGame(SettingGame())
            MenuEvent.OnClickRandomButton -> onStartGame(createRandomList())
            MenuEvent.OnClickRatingButton -> onStartRating()
            MenuEvent.OnClickTrainingButton -> onStartTraining()
            MenuEvent.ResetActions -> onResetAction()
        }
    }

    private fun onStartTraining() {
        viewAction = MenuAction.OpenTraining
    }

    private fun onStartRating() {
        viewAction = MenuAction.OpenRating
    }

    private fun onStartGame(settingGame: SettingGame) {
        viewAction = MenuAction.OpenGame(settingGame)
    }
}