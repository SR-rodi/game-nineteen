package ru.sr.mimeteen.remotedatabase.api

import ru.sr.mimeteen.remotedatabase.model.RatingDto

interface RatingApi {

    suspend fun setNewRating(rating: RatingDto)

    suspend fun getAllRating():List<RatingDto>

    suspend fun getRatingByUseID(userId:String):RatingDto
}