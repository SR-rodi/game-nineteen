package ru.sr.nineteen.presentation.edit.compose.screen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.presentation.edit.compose.view.EditUserNameView
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameEvent
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameState
import ru.sr.nineteen.presentation.edit.viewmodel.EditUserNameViewModel
import ru.sr.nineteen.profile.R
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.Screen

@Composable
fun EditUserNameScreen(userName: String?, viewModel: EditUserNameViewModel = koinViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(EditNameEvent.OnStartScreen(userName))
    }
    Screen(viewModel = viewModel) { state, action, rootController ->
        EditUserNameView(state) { event -> viewModel.obtainEvent(event) }
    }

}
