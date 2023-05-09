package ru.sr.nineteen.presentation.edit.compose.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.presentation.edit.compose.view.EditUserNameView
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameAction
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameEvent
import ru.sr.nineteen.presentation.edit.viewmodel.EditUserNameViewModel
import ru.sr.nineteen.view.Screen

@Composable
fun EditUserNameScreen(
    userName: String?,
    viewModel: EditUserNameViewModel = koinViewModel(),
    getNewName: (newName: String) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(EditNameEvent.OnStartScreen(userName))
    }
    Screen(viewModel = viewModel) { state, action, rootController ->
        EditUserNameView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            is EditNameAction.GoBack -> {
                if (action.newName != null) getNewName(action.newName)
                rootController.popBackStack()
                viewModel.obtainEvent(EditNameEvent.OnResetAction)
            }

            null -> {}
        }
    }

}
