package com.example.nineteen_2_0.presentation.win

import androidx.lifecycle.ViewModel
import com.example.nineteen_2_0.data.database.dao.RatingDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WinViewModel @Inject constructor(
    private val ratingDao: RatingDao
):ViewModel() {
}