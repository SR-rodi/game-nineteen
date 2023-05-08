package ru.sr.nineteen.presentation.compose.screen

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.core.LaunchFlag
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.compose.screen.view.ProfileView
import ru.sr.nineteen.presentation.viewmodel.ProfileAction
import ru.sr.nineteen.presentation.viewmodel.ProfileEvent
import ru.sr.nineteen.presentation.viewmodel.ProfileViewModel
import ru.sr.nineteen.view.Screen

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = koinViewModel()) {


    Screen(viewModel) { state, action, rootController ->
        ProfileView(state){event->viewModel.obtainEvent(event)}

        when(action){
            ProfileAction.OpenSignInScreen -> {
                rootController.push(NavigationTree.SignIn.name)
                viewModel.obtainEvent(ProfileEvent.OnResetAction)
            }
            null ->{}
        }

    }

}