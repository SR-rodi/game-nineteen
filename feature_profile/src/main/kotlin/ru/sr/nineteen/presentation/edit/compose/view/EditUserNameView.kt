package ru.sr.nineteen.presentation.edit.compose.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameEvent
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameState
import ru.sr.nineteen.profile.R
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.BaseProgressIndicator
import ru.sr.nineteen.view.EmailTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserNameView(state: EditNameState, eventHandler: (EditNameEvent) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.profile_edit_name_title),
            textAlign = TextAlign.Center,
            style = GameTheme.fonts.h3.copy(color = GameTheme.colors.textTitle)
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.userName,
            enabled = !state.isLoading,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = GameTheme.colors.blue_500,
                unfocusedBorderColor = GameTheme.colors.blue_500,
                focusedLabelColor = GameTheme.colors.blue_500,
                cursorColor = GameTheme.colors.blue_500,
                selectionColors = TextSelectionColors(
                    handleColor = GameTheme.colors.blue_500,
                    backgroundColor = GameTheme.colors.blue_100
                ),
            ), onValueChange = { newName -> eventHandler(EditNameEvent.OnChangeName(newName)) })

        if (state.isError) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = ru.sr.nineteen.core_ui.R.string.core_ui_error_network),
                textAlign = TextAlign.Center,
                style = GameTheme.fonts.p.copy(color = GameTheme.colors.error)
            )
        }

        BaseProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            isVisible = state.isLoading
        )

        Spacer(modifier = Modifier.height(8.dp))
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