package ru.sr.nineteen.presentation.signin.viewmodel.model

import ru.sr.nineteen.authorization.R

data class SignInState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.auth_error_internet,
    val email: String = "",
    val password: String = "",
    val isErrorEmailValidation: Boolean = false,
    val isErrorPasswordValidation: Boolean = false,
)