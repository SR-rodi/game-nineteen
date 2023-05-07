package ru.sr.nineteen.presentation.resetpassword.viewmodel.model

import ru.sr.nineteen.authorization.R

data class ResetPasswordState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isEmailValidation: Boolean = true,
    val errorMessageId: Int = R.string.auth_error_internet,
    val email: String = "",
)