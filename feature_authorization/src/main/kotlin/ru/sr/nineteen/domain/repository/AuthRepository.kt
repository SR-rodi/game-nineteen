package ru.sr.nineteen.domain.repository

import com.google.firebase.auth.FirebaseUser
import ru.sr.nineteen.domain.AuthUserDomainModel

interface AuthRepository {

   suspend fun getCurrentUser(): FirebaseUser?

   suspend fun createUserWithEmailAndPassword(email:String, password:String): AuthUserDomainModel

   suspend fun signInWithEmailAndPassword(email:String, password:String): AuthUserDomainModel

   fun setToken(token:String)

}