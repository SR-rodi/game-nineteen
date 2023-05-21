package ru.sr.nineteen.data.mapper

import com.google.firebase.auth.FirebaseUser
import ru.sr.mimeteen.remotedatabase.model.UserDto
import ru.sr.nineteen.domain.model.AuthUserDomainModel
import ru.sr.nineteen.presentation.model.AuthUserUiModel

interface AuthDomainMapper {
    fun userDtoToAuthUserDomainModel(user: UserDto): AuthUserDomainModel
}

interface AuthUiMapper {
    fun authUserDomainModelToAuthUser(user: AuthUserDomainModel): AuthUserUiModel
}


class AuthDomainMapperImpl : AuthDomainMapper {

    override fun userDtoToAuthUserDomainModel(user: UserDto) =
        AuthUserDomainModel(user.id, user.email)
}

class AuthUiMapperImpl : AuthUiMapper {
    override fun authUserDomainModelToAuthUser(user: AuthUserDomainModel) =
        AuthUserUiModel(user.token, user.email)

}