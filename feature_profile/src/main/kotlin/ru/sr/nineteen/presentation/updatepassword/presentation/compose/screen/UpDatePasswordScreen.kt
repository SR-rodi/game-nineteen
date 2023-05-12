package ru.sr.nineteen.presentation.updatepassword.presentation.compose.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.presentation.updatepassword.presentation.viewmodel.UpDatePasswordViewModel
import ru.sr.nineteen.presentation.updatepassword.presentation.viewmodel.UpdatePasswordAction
import ru.sr.nineteen.presentation.updatepassword.presentation.viewmodel.UpdatePasswordEvent
import ru.sr.nineteen.presentation.updatepassword.presentation.viewmodel.UpdatePasswordState
import ru.sr.nineteen.profile.R
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.BaseProgressIndicator
import ru.sr.nineteen.view.GameDialog
import ru.sr.nineteen.view.PasswordTextField
import ru.sr.nineteen.view.Screen

@Composable
fun UpDatePasswordScreen(
    viewModel: UpDatePasswordViewModel = koinViewModel(),
    onDismiss: () -> Unit,
) {

    Screen(viewModel = viewModel) { state, action, _ ->
        UpDatePasswordView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            UpdatePasswordAction.OnGoBack -> {
                onDismiss()
                viewModel.obtainEvent(UpdatePasswordEvent.OnResetAction)
            }

            null -> {}
        }
    }
}

@Composable
fun UpDatePasswordView(state: UpdatePasswordState, eventHandler: (UpdatePasswordEvent) -> Unit) {

    GameDialog(onDismiss = { eventHandler(UpdatePasswordEvent.OnClickBackButton) }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.profile_reset_password_title))
            PasswordTextField(
                value = state.oldPassword,
                isError = state.isErrorOldPassword,
                isEnable = !state.isLoading,
                isVisibleHelper = false,
                errorMessage = stringResource(id = R.string.profile_error_old_passwoed),
                hintId = R.string.profile_old_password,
                onValueChange = { password ->
                    eventHandler(
                        UpdatePasswordEvent.OnChangeOldPassword(password)
                    )
                })
            PasswordTextField(
                value = state.newPassword,
                hintId = R.string.profile_new_password,
                isError = state.isErrorNewPassword,
                isEnable = !state.isLoading,
                onValueChange = { password ->
                    eventHandler(
                        UpdatePasswordEvent.OnChangeNewPassword(password)
                    )
                })
            BaseProgressIndicator(isVisible = state.isLoading)
            AnimatedVisibility(visible = state.isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = GameTheme.fonts.p.copy(GameTheme.colors.error),
                    text = stringResource(id = ru.sr.nineteen.core_ui.R.string.core_ui_error_network)
                )
            }

            Row(Modifier.fillMaxWidth()) {
                Box(Modifier.weight(1f)) {
                    ActionButtonView(
                        enabled = !state.isLoading,
                        style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textButton),
                        text = stringResource(id = R.string.profile_edit_button)
                    ) {
                        eventHandler(
                            UpdatePasswordEvent.OnClickUpdatePassword
                        )
                    }
                }
                Spacer(modifier = Modifier.size(8.dp))
                Box(Modifier.weight(1f)) {
                    ActionButtonView(
                        enabled = !state.isLoading,
                        style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textButton),
                        text = stringResource(id = R.string.profile_edit_back)
                    ) {
                        eventHandler(
                            UpdatePasswordEvent.OnClickBackButton
                        )
                    }
                }

            }

        }
    }


}
