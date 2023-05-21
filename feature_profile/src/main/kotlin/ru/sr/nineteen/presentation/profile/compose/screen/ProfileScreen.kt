package ru.sr.nineteen.presentation.profile.compose.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.navgraph.navcomponent.LaunchFlag
import ru.sr.nineteen.navgraph.navcomponent.push
import ru.sr.nineteen.presentation.edit.compose.screen.EditUserNameDialog
import ru.sr.nineteen.presentation.profile.compose.view.ProfileView
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileAction
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileEvent
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileViewModel
import ru.sr.nineteen.presentation.updatepassword.presentation.compose.screen.UpDatePasswordScreen
import ru.sr.nineteen.presentation.warning.compose.screen.ProfileDeleteWarningDialog
import ru.sr.nineteen.composeview.Screen

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) { viewModel.obtainEvent(ProfileEvent.OnStartScreen) }

    Screen(viewModel) { state, action, navController ->

        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
                viewModel.obtainEvent(ProfileEvent.OnSetNewAvatar(uri))
            }

        ProfileView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            ProfileAction.OpenSignInScreen -> {
                navController.push(
                    NavigationTree.SignIn,
                    launchFlag = LaunchFlag.ClearNavGraph
                )
            }

            null -> {}
            ProfileAction.OpenWarningScreen -> {
                ProfileDeleteWarningDialog { event ->
                    viewModel.obtainEvent(event)
                }
            }

            ProfileAction.OpenGallery -> {
                launcher.launch("image/*")
                viewModel.obtainEvent(ProfileEvent.OnResetAction)
            }

            is ProfileAction.OpenEditNameScreen -> {
                EditUserNameDialog(action.userName){event->
                    viewModel.obtainEvent(event)
                }
            }

            ProfileAction.OpenUpdatePasswordScreen -> {
                UpDatePasswordScreen(){
                    viewModel.obtainEvent(ProfileEvent.OnResetAction)
                }
            }
        }

    }
}

