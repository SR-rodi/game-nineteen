package ru.sr.nineteen.presentation.signin.compose.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.presentation.root.ErrorMessageView
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInEvent
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInState
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.EmailTextField
import ru.sr.nineteen.view.PasswordTextField

@Composable
fun SignInView(state: SignInState, eventHandler: (SignInEvent) -> Unit) {

    if (state.isLoading) Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = GameTheme.colors.blue_500)
    }
    Column(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.auth_auth_title),
            style = GameTheme.fonts.h2.copy(color = GameTheme.colors.textTitle)
        )
        Spacer(modifier = Modifier.height(8.dp))
        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            isEnable = !state.isLoading,
            isError = state.isErrorEmailValidation,
            onValueChange = { email -> eventHandler(SignInEvent.OnChangeEmail(email)) }) {
            eventHandler(SignInEvent.OnClearEmail)
        }

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            isEnable = !state.isLoading,
            isError = state.isErrorPasswordValidation,
            value = state.password,
            onValueChange = { password -> eventHandler(SignInEvent.OnChangePassword(password)) }
        )
        val forgotPasswordClickable = if (state.isLoading) Modifier
        else Modifier.clickable { eventHandler(SignInEvent.OnClickForgotPasswordButton) }
        Box(
            modifier = Modifier
                .clip(GameTheme.shapes.medium)
                .align(Alignment.End)
                .padding(vertical = 4.dp)
                .then(forgotPasswordClickable)
        ) {

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = stringResource(id = R.string.auth_forgot_password),
                style = GameTheme.fonts.p.copy(color = GameTheme.colors.textButton)
            )
        }
        ErrorMessageView(
            isVisible = state.isError,
            message = stringResource(id = state.errorMessage)
        )
        ActionButtonView(
            text = stringResource(id = R.string.auth_to_come_in),
            enabled = !state.isLoading,
            padding = PaddingValues()
        ) { eventHandler(SignInEvent.OnClickSignInButton) }

        Spacer(modifier = Modifier.height(8.dp))

        OrDivider()
        Spacer(modifier = Modifier.height(8.dp))
        ActionButtonView(
            text = stringResource(id = R.string.auth_to_come_in_guest),
            padding = PaddingValues(),
            enabled = !state.isLoading,
            isOutLine = true
        ) { eventHandler(SignInEvent.OnOpenWarning) }
        Spacer(modifier = Modifier.height(8.dp))
        ActionButtonView(
            text = stringResource(id = R.string.auth_registration_button),
            padding = PaddingValues(),
            isOutLine = true,
            enabled = !state.isLoading,
        ) { eventHandler(SignInEvent.OnClickRegistrationButton) }
    }
}


@Composable
fun OrDivider(color: Color = GameTheme.colors.blue_100) {

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(color)
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Или", style = GameTheme.fonts.p.copy(color = color)
        )
        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(color)
        )

    }
}