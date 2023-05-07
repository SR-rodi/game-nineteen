package ru.sr.nineteen.presentation.registration.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationEvent
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationState
import ru.sr.nineteen.presentation.root.ErrorMessageView
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.EmailTextField
import ru.sr.nineteen.view.PasswordTextField

@Composable
fun RegistrationView(state: RegistrationState, eventHandler: (RegistrationEvent) -> Unit) {
    if (state.isLoading) Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = GameTheme.colors.blue_500)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.auth_registration_title),
            style = GameTheme.fonts.h2.copy(color = GameTheme.colors.textTitle)
        )
        Spacer(modifier = Modifier.height(8.dp))
        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            isError = state.isErrorEmailValidation,
            isEnable = !state.isLoading,
            onValueChange = { email -> eventHandler(RegistrationEvent.OnChangeEmail(email)) }) {
            eventHandler(RegistrationEvent.OnClearEmail)
        }

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            isRepeatMode = true,
            value = state.password,
            isError = state.isErrorPasswordValidation,
            isEnable = !state.isLoading,
            onValueChange = { password ->
                eventHandler(RegistrationEvent.OnChangePassword(password))
            })
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.repeatPassword,
            isError = state.isErrorPasswordValidation,
            isEnable = !state.isLoading,
            onValueChange = { password ->
                eventHandler(RegistrationEvent.OnChangeRepeatPassword(password))
            })
        Spacer(modifier = Modifier.height(8.dp))
        ErrorMessageView(
            isVisible = state.isError,
            message = stringResource(id = state.errorMessage)
        )
        ActionButtonView(text = stringResource(id = R.string.auth_registration_button)) {
            eventHandler(RegistrationEvent.OnRegistrationButtonClick)
        }

    }
}