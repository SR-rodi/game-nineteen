package ru.sr.nineteen.data.mapper

import ru.sr.nineteen.domain.model.RatingDomainModel
import ru.sr.nineteen.presentation.model.RatingUIModel

interface RatingUiMapper {
    fun ratingDomainToUI(model: RatingDomainModel): RatingUIModel
    fun ratingUiToDomain(model: RatingUIModel): RatingDomainModel

}