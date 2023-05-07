package ru.sr.nineteen.presentation.resetpassword.compose.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.presentation.resetpassword.viewmodel.model.ResetPasswordEvent
import ru.sr.nineteen.presentation.resetpassword.viewmodel.model.ResetPasswordState
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.EmailTextField

@Composable
fun ResetPasswordView(state: ResetPasswordState, eventHandler: (ResetPasswordEvent) -> Unit) {

    if (state.isLoading) Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = GameTheme.colors.blue_500)
    }

    Column(
        Modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.auth_send_reset_password_title),
            style = GameTheme.fonts.h2.copy(color = GameTheme.colors.textTitle)
        )
        Spacer(modifier = Modifier.weight(1f))
        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            isEnable = !state.isLoading,
            isError = !state.isEmailValidation,
            imeAction = ImeAction.Done,
            onValueChange = { email -> eventHandler(ResetPasswordEvent.OnChangeEmail(email)) }) {
            eventHandler(ResetPasswordEvent.OnClearEmail)
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(visible = state.isError) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = GameTheme.fonts.p.copy(GameTheme.colors.error),
                    text = stringResource(id = state.errorMessageId)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
        ActionButtonView(
            padding = PaddingValues(),
            enabled = !state.isLoading,
            text = stringResource(id = R.string.auth_send_reset_password_title)
        ) {
            eventHandler(ResetPasswordEvent.OnClickSendPassword)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}