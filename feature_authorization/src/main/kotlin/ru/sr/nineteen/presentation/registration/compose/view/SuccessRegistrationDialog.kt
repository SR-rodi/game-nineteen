package ru.sr.nineteen.presentation.registration.compose.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationEvent
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.composeview.ActionButtonView
import ru.sr.nineteen.composeview.GameDialog

@Composable
fun SuccessRegistrationDialog(eventHandler: (RegistrationEvent) -> Unit) {

    GameDialog(onDismiss = { eventHandler(RegistrationEvent.OnResetAction) }) {
        Column(Modifier.padding(16.dp)) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.auth_send_reset_password_success_title),
                style = GameTheme.fonts.h2.copy(GameTheme.colors.textTitle),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.auth_registration_success_message),
                style = GameTheme.fonts.h4.copy(GameTheme.colors.text)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ActionButtonView(
                text = stringResource(id = R.string.auth_registration_success_Button),
                padding = PaddingValues()
            ) {
                eventHandler(RegistrationEvent.OnStartSing)
            }
        }
    }

}