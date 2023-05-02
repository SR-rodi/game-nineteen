package ru.sr.nineteen.data.repository

import ru.sr.nineteen.data.database.dao.RatingDao
import ru.sr.nineteen.data.database.entity.RatingEntity

class RatingRepository(
    private val ratingDao: RatingDao
) {

    fun getRatingList() = ratingDao.getAll()

    fun insertNewRating(ratingEntity: RatingEntity) = ratingDao.insert(ratingEntity)
}