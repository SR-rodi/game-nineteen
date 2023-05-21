package ru.sr.nineteen.domain.repositoty

import ru.sr.nineteen.domain.model.RatingDomainModel

interface RatingRemoteRepository {

    suspend fun getTopTenRating():List<RatingDomainModel>

    suspend fun showMyRating():List<RatingDomainModel>?
}