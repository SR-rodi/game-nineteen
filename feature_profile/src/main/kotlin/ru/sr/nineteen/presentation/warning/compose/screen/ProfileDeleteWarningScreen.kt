package ru.sr.nineteen.presentation.warning.compose.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.presentation.warning.viewmodel.ProfileDeleteAction
import ru.sr.nineteen.presentation.warning.viewmodel.ProfileDeleteEvent
import ru.sr.nineteen.presentation.warning.viewmodel.ProfileDeleteState
import ru.sr.nineteen.presentation.warning.viewmodel.ProfileDeleteWarningViewModel
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.BaseProgressIndicator
import ru.sr.nineteen.view.Screen
import ru.sr.nineteen.core_ui.R
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileEvent
import ru.sr.nineteen.view.GameDialog

@Composable
fun ProfileDeleteWarningDialog(
    viewModel: ProfileDeleteWarningViewModel = koinViewModel(),
    eventHandler: (ProfileEvent) -> Unit,
) {

    Screen(viewModel = viewModel) { state, action, _ ->

        ProfileDeleteWarningView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            ProfileDeleteAction.OpenSignInScreen -> {
                eventHandler(ProfileEvent.OnSuccessDeleteAccount)
                viewModel.obtainEvent(ProfileDeleteEvent.OnResetAction)
            }

            ProfileDeleteAction.PopToBackStack -> {
                eventHandler(ProfileEvent.DismissDeleteDialog)
                viewModel.obtainEvent(ProfileDeleteEvent.OnResetAction)
            }

            null -> {}
            ProfileDeleteAction.CloseDialog -> eventHandler(ProfileEvent.DismissDeleteDialog)
        }
    }
}

@Composable
fun ProfileDeleteWarningView(
    state: ProfileDeleteState,
    eventHandler: (ProfileDeleteEvent) -> Unit,
) {
    GameDialog(onDismiss = { eventHandler(ProfileDeleteEvent.OnDismiss) }) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Вы уверены что хотите удалить свой Аккаунт?",
                style = GameTheme.fonts.h2.copy(color = GameTheme.colors.textTitle),
                textAlign = TextAlign.Center
            )
            BaseProgressIndicator(modifier = Modifier.padding(8.dp), isVisible = state.isLoading)
            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(),
                visible = state.isError
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.core_ui_error_network),
                    textAlign = TextAlign.Center,
                    style = GameTheme.fonts.p.copy(GameTheme.colors.error)
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    ActionButtonView(
                        text = stringResource(id = R.string.core_ui_yes),
                        isOutLine = true
                    ) {
                        eventHandler(ProfileDeleteEvent.OnClickYesButton)
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    ActionButtonView(stringResource(id = R.string.core_ui_no)) {
                        eventHandler(ProfileDeleteEvent.OnClickNoButton)
                    }
                }
            }
        }
    }

}
