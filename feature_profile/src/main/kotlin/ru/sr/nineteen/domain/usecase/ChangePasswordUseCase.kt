package ru.sr.nineteen.domain.usecase

import androidx.compose.ui.input.pointer.PointerEventPass

interface ChangePasswordUseCase {

   suspend fun update(newPass: String)
}