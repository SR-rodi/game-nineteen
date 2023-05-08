package ru.sr.nineteen.data.mapper

import ru.sr.mimeteen.remotedatabase.model.UserDto
import ru.sr.nineteen.domain.model.ProfileUserDomainModel

class ProfileDomainMapperImpl(): ProfileDomainMapper {


    override  fun userDtoToProfileUserDomainModel(userDto: UserDto) = ProfileUserDomainModel(
        email = userDto.email, id = userDto.id, userDto.name
    )
}


interface ProfileDomainMapper {
    fun userDtoToProfileUserDomainModel(userDto: UserDto): ProfileUserDomainModel
}

