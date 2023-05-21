package ru.sr.nineteen.data.mapper.impl

import ru.sr.nineteen.data.mapper.RatingUiMapper
import ru.sr.nineteen.domain.model.RatingDomainModel
import ru.sr.nineteen.presentation.model.RatingUIModel

class RatingUiMapperImpl:RatingUiMapper {
    override fun ratingDomainToUI(model: RatingDomainModel): RatingUIModel {
        return RatingUIModel(
            userId = model.userId,
            userName = model.userName,
            steps = model.steps,
            mode = model.mode,
            userAvatar = model.userAvatar,
            time = model.time,
            pointer = model.pointer
        )
    }

    override fun ratingUiToDomain(model: RatingUIModel): RatingDomainModel {
        return RatingDomainModel(
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