package ru.sr.nineteen.data.mapper

import com.google.firebase.auth.FirebaseUser
import ru.sr.nineteen.domain.AuthUserDomainModel
import ru.sr.nineteen.presentation.model.AuthUserUiModel

interface AuthDomainMapper {
    fun firebaseUserToAuthUserDomainModel(user: FirebaseUser): AuthUserDomainModel
}

interface AuthUiMapper {
    fun authUserDomainModelToAuthUser(user: AuthUserDomainModel): AuthUserUiModel
}


class AuthDomainMapperImpl: AuthDomainMapper {

    override fun firebaseUserToAuthUserDomainModel(user: FirebaseUser) =
        AuthUserDomainModel(user.uid, user.email)
}

class AuthUiMapperImpl: AuthUiMapper {
    override fun authUserDomainModelToAuthUser(user: AuthUserDomainModel)=
        AuthUserUiModel(user.token,user.email)

}