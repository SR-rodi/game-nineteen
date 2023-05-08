package ru.sr.nineteen.presentation.model.mapper

import ru.sr.nineteen.domain.model.ProfileUserDomainModel
import ru.sr.nineteen.presentation.model.ProfileUserUIModel

interface ProfileUIMapper {

    fun profileDomainToProfileUi(domainModel: ProfileUserDomainModel): ProfileUserUIModel
}

class ProfileUIMapperImpl() : ProfileUIMapper {
    override fun profileDomainToProfileUi(domainModel: ProfileUserDomainModel) = ProfileUserUIModel(
        email = domainModel.email ?: "",
        id = domainModel.id,
        name = domainModel.name ?: ""
    )
}