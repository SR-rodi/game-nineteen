package ru.sr.nineteen.data.repository

import com.google.firebase.FirebaseException

class FirebaseNotAuth(errorMessage: String = "неудолось выполнинить запрос") :
    FirebaseException(errorMessage)