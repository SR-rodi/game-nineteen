package com.example.nineteen_2_0.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nineteen_2_0.data.gameitem.SettingGame
import com.example.nineteen_2_0.data.itemlist.RandomItemList
import com.example.nineteen_2_0.data.repository.GameRepository
import com.example.nineteen_2_0.utility.toSettingGame

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
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