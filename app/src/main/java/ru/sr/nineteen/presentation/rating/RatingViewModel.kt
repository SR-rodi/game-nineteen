package ru.sr.nineteen.presentation.rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sr.nineteen.data.database.entity.RatingEntity
import ru.sr.nineteen.data.repository.RatingRepository

class RatingViewModel(
    private val rattingRepository: RatingRepository
) : ViewModel() {


    private val _ratingList = MutableStateFlow<List<RatingEntity>>(emptyList())
    val ratingList = _ratingList.asStateFlow()

    fun getRatting(){
        viewModelScope.launch(Dispatchers.IO) {
            val ratting = rattingRepository.getRatingList()
            val a = ratting.sortedWith(compareBy{ it.time })
           _ratingList.value = a
        }
    }
}