package ru.sr.nineteen.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus
import ru.sr.nineteen.data.gameitem.SettingGame
import ru.sr.nineteen.data.itemlist.RandomItemList
import ru.sr.nineteen.data.repository.GameRepository
import ru.sr.nineteen.utility.toSettingGame

class StartViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _nextButton = MutableStateFlow(false)
    val next = _nextButton.asStateFlow()

    private val _itemList = MutableStateFlow(SettingGame())
    val list = _itemList.asStateFlow()

    fun createRandomList(): SettingGame {
        val settingGame = SettingGame()
        settingGame.list = RandomItemList().create()
        settingGame.gameMode = "random"
        return settingGame
    }

    fun getGameList() {
        gameRepository.getGameList().onEach { entity ->
            if (entity != null) {
                if (entity.list.isNotEmpty()) {
                    _nextButton.value = true
                    _itemList.value = entity.toSettingGame()
                } else _nextButton.value = false
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }
}