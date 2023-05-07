package ru.sr.nineteen.domain

interface Validation {

    fun emailValidation(email:String):Boolean

    fun passwordValidation(password:String):Boolean
}