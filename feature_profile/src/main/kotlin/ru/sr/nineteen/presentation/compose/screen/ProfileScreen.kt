package ru.sr.nineteen.presentation.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.core.LaunchFlag
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.compose.view.ProfileView
import ru.sr.nineteen.presentation.viewmodel.ProfileAction
import ru.sr.nineteen.presentation.viewmodel.ProfileEvent
import ru.sr.nineteen.presentation.viewmodel.ProfileViewModel
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.utils.presentAlertDialog
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.Screen

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = koinViewModel()) {

    Screen(viewModel) { state, action, rootController ->
        ProfileView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            ProfileAction.OpenSignInScreen -> {
                rootController.push(
                    NavigationTree.SignIn.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
                viewModel.obtainEvent(ProfileEvent.OnResetAction)
            }

            null -> {}
            ProfileAction.OpenWarningScreen -> {
                rootController.findModalController().presentAlertDialog {
                    ProfileDeleteWarningScreen()
                }
                viewModel.obtainEvent(ProfileEvent.OnResetAction)
            }
        }

    }

}

