package ru.sr.nineteen.data.repository

import ru.sr.mimeteen.remotedatabase.api.RatingApi
import ru.sr.nineteen.data.mapper.RatingDataMapper
import ru.sr.nineteen.data.mapper.RatingDomainMapper
import ru.sr.nineteen.domain.model.RatingDomainModel
import ru.sr.nineteen.domain.repositoty.RatingRemoteRepository

class RatingRemoteRepositoryImpl(
    private val ratingApi: RatingApi,
    private val domainMapper: RatingDomainMapper,
) : RatingRemoteRepository {

    override suspend fun getTopTenRating(): List<RatingDomainModel> {
        return ratingApi.getTopTenRating().map { rating ->
            domainMapper.ratingDtoToRatingDomainModel(rating)
        }
    }

    override suspend fun showMyRating(): List<RatingDomainModel>? {
        return ratingApi.showMyRating()?.map { dto ->
            domainMapper.ratingDtoToRatingDomainModel(dto)
        }
    }
}