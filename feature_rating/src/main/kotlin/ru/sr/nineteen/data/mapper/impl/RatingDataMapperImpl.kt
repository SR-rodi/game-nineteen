package ru.sr.nineteen.data.mapper.impl

import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.nineteen.data.mapper.RatingDataMapper
import ru.sr.nineteen.domain.model.RatingDomainModel

class RatingDataMapperImpl : RatingDataMapper {
    override fun ratingDomainToRatingDto(model: RatingDomainModel): RatingDto {
        return RatingDto(
            userId = model.userId,
            userName = model.userName,
            steps = model.steps,
            mode = model.mode,
            userAvatar = model.userAvatar,
            time = model.time,
            pointer = model.pointer
        )
    }
}