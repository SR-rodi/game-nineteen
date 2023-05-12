package ru.sr.mimeteen.database.repository

import ru.sr.mimeteen.database.dao.RatingDao
import ru.sr.mimeteen.remotedatabase.api.RatingApi
import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.nineteen.data.database.entity.RatingEntity

class RatingRepository(
    private val ratingDao: RatingDao,
) {

    fun getRatingList() = ratingDao.getAll()

    fun insertNewRating(ratingEntity: RatingEntity) = ratingDao.insert(ratingEntity)
}

interface RemoteRatingRepository {

    suspend fun insertNewRating(rating: RatingDto)
}


class RemoteRatingRepositoryImpl(
    private val remoteRatingRepository: RatingApi,
) : RemoteRatingRepository {

    override suspend fun insertNewRating(rating: RatingDto) {
        remoteRatingRepository.setNewRating(rating)
    }
}