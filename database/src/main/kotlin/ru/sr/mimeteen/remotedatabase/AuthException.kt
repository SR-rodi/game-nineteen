package ru.sr.mimeteen.remotedatabase

class FirebaseNotAuth(errorMessage: String = "не неайден пользователь") :
    Exception(errorMessage)

class  FirebaseNoEmailVerifications(errorMessage: String = "не прошла валидация") :
    Exception(errorMessage)