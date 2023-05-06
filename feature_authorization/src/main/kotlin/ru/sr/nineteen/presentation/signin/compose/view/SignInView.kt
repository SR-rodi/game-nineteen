package ru.sr.nineteen.presentation.signin.compose.view

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.presentation.signin.viewmodel.SignInEvent
import ru.sr.nineteen.presentation.signin.viewmodel.SignInState
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.EmailTextField
import ru.sr.nineteen.view.PasswordTextField

@Composable
fun SignInView(state: SignInState, eventHandler: (SignInEvent) -> Unit) {

        Column(
            Modifier
                .padding(16.dp)

        ) {
            EmailTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                onValueChange = { email -> eventHandler(SignInEvent.OnChangeEmail(email)) }) {
                eventHandler(SignInEvent.OnClearEmail)
            }

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = { password -> eventHandler(SignInEvent.OnChangePassword(password)) }
            )
            Box(modifier = Modifier
                .clip(GameTheme.shapes.medium)
                .align(Alignment.End)
                .padding(vertical = 4.dp)
                .clickable { eventHandler(SignInEvent.OnClickForgotPasswordButton) }
            ) {

                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = stringResource(id = R.string.auth_forgot_password),
                    style = GameTheme.fonts.p.copy(color = GameTheme.colors.textButton)
                )
            }

            ActionButtonView(
                text = stringResource(id = R.string.auth_to_come_in),
                padding = PaddingValues()
            ) { eventHandler(SignInEvent.OnClickSignInButton) }

            Spacer(modifier = Modifier.height(8.dp))

            OrDivider()
            Spacer(modifier = Modifier.height(8.dp))
            ActionButtonView(
                text = stringResource(id = R.string.auth_to_come_in_guest),
                padding = PaddingValues(),
                isOutLine = true
            ) { eventHandler(SignInEvent.OnOpenWarning) }
            Spacer(modifier = Modifier.height(8.dp))
            ActionButtonView(
                text = stringResource(id = R.string.auth_registration_button),
                padding = PaddingValues(),
                isOutLine = true
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