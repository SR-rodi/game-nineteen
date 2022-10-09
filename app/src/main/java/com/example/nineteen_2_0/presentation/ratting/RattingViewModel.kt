package com.example.nineteen_2_0.presentation.ratting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nineteen_2_0.data.database.entity.RattingEntity
import com.example.nineteen_2_0.data.repository.RatingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RattingViewModel @Inject constructor(
    private val rattingRepository: RatingRepository
) : ViewModel() {


    private val _rattingList = MutableStateFlow<List<RattingEntity>>(emptyList())
    val rattingList = _rattingList.asStateFlow()

    fun getRatting(){
        viewModelScope.launch(Dispatchers.IO) {
            val ratting = rattingRepository.getRatingList()
            val a = ratting.sortedWith(compareBy{ it.time })
           _rattingList.value = a
        }
    }
}