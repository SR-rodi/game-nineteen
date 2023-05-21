package ru.sr.nineteen.data.mapper.impl

import ru.sr.mimeteen.remotedatabase.model.RatingDto
import ru.sr.nineteen.data.mapper.RatingDomainMapper
import ru.sr.nineteen.domain.model.RatingDomainModel

class RatingDomainMapperImpl : RatingDomainMapper {

    override fun ratingDtoToRatingDomainModel(dto: RatingDto): RatingDomainModel {
        return RatingDomainModel(
            userId = dto.userId,
            userName = dto.userName,
            steps = dto.steps,
            mode = dto.mode,
            userAvatar = dto.userAvatar,
            time = dto.time,
            pointer = dto.pointer
        )
    }
}