package ru.sr.nineteen.presentation.viewmodel.model

import ru.sr.nineteen.presentation.model.RatingUIModel

data class RatingState(
    val isLoading: Boolean = false,
    val isMyResultLoading: Boolean = false,
    val isError: Boolean = false,
    val ratingItems: List<RatingUIModel> = emptyList(),
    val myRating: RatingUIModel? = null,
    val myPosition: Int? = null,
)