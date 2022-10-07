package com.example.nineteen_2_0.presentation.gamefield

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nineteen_2_0.data.database.entity.GameListEntity
import com.example.nineteen_2_0.data.database.entity.RatingEntity
import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.data.gameitem.SettingGame
import com.example.nineteen_2_0.data.repository.GameRepository
import com.example.nineteen_2_0.data.repository.RatingRepository
import com.example.nineteen_2_0.logic.ClassicGameLogic
import com.example.nineteen_2_0.logic.WinLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameFieldViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val ratingRepository: RatingRepository
) : ViewModel() {

    var itemList = mutableListOf<GameItem>()
    private val game by lazy { ClassicGameLogic(itemList) }
    private val winLogic by lazy { WinLogic(itemList) }
    private val positionList = mutableListOf<Int>()
    private var timerCounter = 0
    private var stepCounter = 1

    private val _isWin = MutableStateFlow(false)
    val isWin = _isWin.asStateFlow()

    private val _statistics = MutableStateFlow(Pair(0,0))
    val statistics = _statistics.asStateFlow()


    fun playGame(position: Int): List<Int> {
        game.selectItem(position)
        positionList.add(position)
        return if (positionList.size == 2) {
            positionList.sort()
            val first = positionList.first()
            val second = positionList.last()
            positionList.clear()
            return if (first == second) emptyList()
            else {
                val deleteList = game.start(first, second).toMutableList()
                deleteList.add(first)
                deleteList.add(second)
                _isWin.value = winLogic.checkWin()
                deleteList
            }
        } else emptyList()
    }

    fun addList() = game.addList()

    fun help() = game.helpButton()

    fun addDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.deleteItemList()
            gameRepository.insertItemList(GameListEntity(itemList,timerCounter,stepCounter))
        }
    }

    fun startSetting(settingGame: SettingGame) {
        itemList = settingGame.list.toMutableList()
        timerCounter = settingGame.time
        stepCounter = settingGame.stepCount
    }

    fun deleteDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.deleteItemList()
        }
    }

    fun counterStep() {
        stepCounter++
    }

    fun startTimer() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                timerCounter++
                delay(1000)
                _statistics.value = Pair(timerCounter,stepCounter)
            }
        }
    }

    fun addRatingDatabase() {
        ratingRepository.insertNewRating(RatingEntity(timerCounter,stepCounter))
    }
}
