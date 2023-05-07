package ru.sr.nineteen.presentation.resetpassword.compose.screen

import androidx.compose.foundation.layout.Column
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
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.core.LaunchFlag
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView

@Composable
fun SuccessSendMailResetPasswordScreen(rootController: RootController) {

    Column (Modifier.padding(16.dp)){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.auth_send_reset_password_success_title),
            style = GameTheme.fonts.h2.copy(GameTheme.colors.textTitle),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.auth_send_reset_password_success_message),
            style = GameTheme.fonts.h4.copy(GameTheme.colors.textTitle),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        ActionButtonView(text = stringResource(id = R.string.auth_send_reset_password_success_button)) {
            rootController.push(NavigationTree.SignIn.name, launchFlag = LaunchFlag.SingleNewTask)
        }
    }
}