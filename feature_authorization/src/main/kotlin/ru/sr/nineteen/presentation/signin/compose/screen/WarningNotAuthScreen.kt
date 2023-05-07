package ru.sr.nineteen.presentation.signin.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
fun WarningNotAuthScreen(rootController: RootController) {

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

        ActionButtonView(
            text = stringResource(id = ru.sr.nineteen.core_ui.R.string.core_ui_next_button),
            padding = PaddingValues()
        ) {
            rootController.push(NavigationTree.Menu.name, launchFlag = LaunchFlag.ClearPrevious)
        }

    }

}