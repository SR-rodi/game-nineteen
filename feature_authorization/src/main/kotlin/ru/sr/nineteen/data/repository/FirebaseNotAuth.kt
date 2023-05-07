package ru.sr.nineteen.data.repository

import com.google.firebase.FirebaseException

class FirebaseNotAuth(errorMessage: String = "не неайден пользователь") :
    FirebaseException(errorMessage)

class  FirebaseNoEmailVerifications(errorMessage: String = "не прошла валидация") :
    FirebaseException(errorMessage)