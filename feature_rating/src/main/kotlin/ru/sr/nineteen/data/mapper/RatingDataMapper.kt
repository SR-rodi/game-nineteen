package ru.sr.nineteen.data.mapper

import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.nineteen.domain.model.RatingDomainModel

interface RatingDataMapper {

    fun ratingDomainToRatingDto(model: RatingDomainModel): RatingDto
}