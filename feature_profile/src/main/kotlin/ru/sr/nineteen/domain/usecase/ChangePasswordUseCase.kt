package ru.sr.nineteen.domain.usecase

interface ChangePasswordUseCase {

   suspend fun update(oldPassword:String,newPass: String)
}