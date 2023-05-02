package com.example.nineteen_2_0.presentation.rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nineteen_2_0.data.database.entity.RatingEntity
import com.example.nineteen_2_0.data.repository.RatingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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