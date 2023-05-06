package ru.sr.nineteen.domain.usecase

import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.firebase.auth.FirebaseUser
import ru.sr.nineteen.domain.AuthUserDomainModel

interface SignInWithEmailUseCase {

    suspend fun signIn(email: String,password:String):AuthUserDomainModel
}