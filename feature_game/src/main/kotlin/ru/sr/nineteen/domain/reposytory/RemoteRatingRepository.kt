package ru.sr.nineteen.domain.reposytory

import ru.sr.mimeteen.remotedatabase.api.RatingApi
import ru.sr.nineteen.data.mapper.GameRatingMapper
import ru.sr.nineteen.domain.model.GameRating

interface RemoteRatingRepository {

    suspend fun setNewRating(gameRating: GameRating)

    suspend fun getCurrentRating(): GameRating?
}

class RemoteRatingRepositoryImpl(
    private val ratingApi: RatingApi,
    private val ratingMapper: GameRatingMapper,
):RemoteRatingRepository {

    override suspend fun setNewRating(gameRating: GameRating) {
        ratingApi.setNewRating(ratingMapper.gameRatingToRatingDto(gameRating))
    }

    override suspend fun getCurrentRating(): GameRating? {
        return ratingApi.getRatingByUseID()?.let { ratingMapper.ratingDtoToGameRating(it) }
    }
}