package ru.sr.nineteen.data

import android.util.Patterns
import ru.sr.nineteen.domain.Validation

internal class ValidationImpl : Validation {

    override fun emailValidation(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun passwordValidation(password: String): Boolean {
        val passwordPattern = "(?=.*[a-z])(?=.*\\d)"
        val reg = Regex(passwordPattern)

        return reg.find(password) != null && password.length >= 8
    }
}