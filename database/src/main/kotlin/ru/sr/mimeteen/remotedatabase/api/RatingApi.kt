package ru.sr.mimeteen.remotedatabase.api

import ru.sr.mimeteen.remotedatabase.model.RatingDto

interface RatingApi {

    suspend fun setNewRating(rating: RatingDto)

    suspend fun getTopTenRating():List<RatingDto>

    suspend fun getRatingByUseID():RatingDto

    suspend fun showMyRating():List<RatingDto>?
}