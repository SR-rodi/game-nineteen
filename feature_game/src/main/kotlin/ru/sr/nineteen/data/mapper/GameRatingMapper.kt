package ru.sr.nineteen.data.mapper

import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.nineteen.domain.model.GameRating

interface GameRatingMapper {

    fun ratingDtoToGameRating(rating:RatingDto):GameRating
    fun gameRatingToRatingDto(rating:GameRating):RatingDto
}