package ru.sr.nineteen.presentation.edit.compose.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameEvent
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameState
import ru.sr.nineteen.profile.R
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserNameView(state: EditNameState, eventHandler: (EditNameEvent) -> Unit) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = stringResource(id = R.string.profile_edit_name_title))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.userName,
            enabled = !state.isLoading,
            onValueChange = { newName -> eventHandler(EditNameEvent.OnChangeName(newName)) })

        if (state.isError) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.profile_error),
                textAlign = TextAlign.Center,
                style = GameTheme.fonts.p.copy(color = GameTheme.colors.error)
            )
        }

        AnimatedVisibility(visible = state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(30.dp)
                    .fillMaxWidth()
            )
        }

        Row {
            Box(modifier = Modifier.weight(1f)) {
                ActionButtonView(
                    enabled = !state.isLoading,
                    text = stringResource(id = R.string.profile_edit_back),
                    style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textButton)
                ) {
                    eventHandler(EditNameEvent.OnClickBackButton)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                ActionButtonView(
                    enabled = !state.isLoading,
                    text = stringResource(id = R.string.profile_edit_button),
                    style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textButton)
                ) {
                    eventHandler(EditNameEvent.OnClickSaveButton)
                }
            }

        }

    }
}