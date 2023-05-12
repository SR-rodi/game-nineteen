package ru.sr.nineteen.presentation.edit.compose.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.events.EventHandler
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.presentation.edit.compose.view.EditUserNameView
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameAction
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameEvent
import ru.sr.nineteen.presentation.edit.viewmodel.EditUserNameViewModel
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileEvent
import ru.sr.nineteen.view.Screen

@Composable
fun EditUserNameDialog(
    userName: String?,
    viewModel: EditUserNameViewModel = koinViewModel(),
    eventHandler: (ProfileEvent) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(EditNameEvent.OnStartScreen(userName))
    }
    Screen(viewModel = viewModel) { state, action, _ ->
        EditUserNameView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            is EditNameAction.GoBack -> {
                eventHandler(ProfileEvent.OnDismissEditUserNameDialog(action.newName))
                viewModel.obtainEvent(EditNameEvent.OnResetAction)
            }

            null -> {}
        }
    }

}
