package ru.sr.nineteen.data.mapper.impl

import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.nineteen.data.mapper.GameRatingMapper
import ru.sr.nineteen.domain.model.GameRating

class GameRatingMapperImpl : GameRatingMapper {
    override fun ratingDtoToGameRating(rating: RatingDto): GameRating {
        return GameRating(
            steps = rating.steps,
            gameMode = rating.mode,
            time = rating.time,
        )
    }

    override fun gameRatingToRatingDto(rating: GameRating): RatingDto {
       return RatingDto(
            steps = rating.steps,
            mode = rating.gameMode,
            time = rating.time,
        )
    }
}