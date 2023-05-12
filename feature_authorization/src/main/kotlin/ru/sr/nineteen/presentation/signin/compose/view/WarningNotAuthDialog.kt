package ru.sr.nineteen.presentation.signin.compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInEvent
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.GameDialog

@Composable
fun WarningNotAuthDialog(eventHandler: (SignInEvent) -> Unit) {


    GameDialog(onDismiss = { eventHandler(SignInEvent.OnResetAction) }) {
        Column(Modifier.padding(16.dp)) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.auth_warning_not_auth_title),
                style = GameTheme.fonts.h2.copy(GameTheme.colors.textTitle),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.auth_warning_not_auth_message),
                style = GameTheme.fonts.h4.copy(GameTheme.colors.text)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ActionButtonView(
                text = stringResource(id = ru.sr.nineteen.core_ui.R.string.core_ui_next_button),
                padding = PaddingValues()
            ) {
                eventHandler(SignInEvent.OnClickSkipAuthButton)
            }
        }
    }
}


