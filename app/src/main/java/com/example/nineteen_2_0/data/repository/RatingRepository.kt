package com.example.nineteen_2_0.data.repository

import com.example.nineteen_2_0.data.database.dao.RatingDao
import com.example.nineteen_2_0.data.database.entity.RattingEntity
import javax.inject.Inject

class RatingRepository @Inject constructor(
    private val ratingDao: RatingDao
) {

    fun getRatingList() = ratingDao.getAll()

    fun insertNewRating(ratingEntity: RattingEntity) = ratingDao.insert(ratingEntity)
}