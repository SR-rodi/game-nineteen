package ru.sr.nineteen.presentation.profile.compose.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.core.LaunchFlag
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.edit.compose.screen.EditUserNameScreen
import ru.sr.nineteen.presentation.edit.viewmodel.EditNameEvent
import ru.sr.nineteen.presentation.profile.compose.view.ProfileView
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileAction
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileEvent
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileViewModel
import ru.sr.nineteen.presentation.updatepassword.presentation.compose.screen.UpDatePasswordScreen
import ru.sr.nineteen.presentation.warning.compose.screen.ProfileDeleteWarningScreen
import ru.sr.nineteen.utils.presentAlertDialog
import ru.sr.nineteen.view.Screen

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) { viewModel.obtainEvent(ProfileEvent.OnStartScreen) }

    Screen(viewModel) { state, action, rootController ->

        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
                viewModel.obtainEvent(ProfileEvent.OnSetNewAvatar(uri))
            }

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

            ProfileAction.OpenGallery -> {
                launcher.launch("image/*")
                viewModel.obtainEvent(ProfileEvent.OnResetAction)
            }

            is ProfileAction.OpenEditNameScreen -> {
                rootController.findModalController()
                    .presentAlertDialog(closeOnBackdropClick = false) {
                        EditUserNameScreen(action.userName) { newName ->
                            viewModel.obtainEvent(ProfileEvent.OnChangeName(newName))
                        }
                    }
                viewModel.obtainEvent(ProfileEvent.OnResetAction)
            }

            ProfileAction.OpenUpdatePasswordScreen -> {
                rootController.findModalController()
                    .presentAlertDialog(closeOnBackdropClick = false) {
                        UpDatePasswordScreen()
                    }
                viewModel.obtainEvent(ProfileEvent.OnResetAction)
            }
        }

    }
}

