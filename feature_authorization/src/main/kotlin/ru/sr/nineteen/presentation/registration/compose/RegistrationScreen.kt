package ru.sr.nineteen.presentation.registration.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.navgraph.navcomponent.LaunchFlag
import ru.sr.nineteen.navgraph.navcomponent.push
import ru.sr.nineteen.presentation.registration.compose.view.RegistrationView
import ru.sr.nineteen.presentation.registration.compose.view.SuccessRegistrationDialog
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationAction
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationEvent
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationViewModel
import ru.sr.nineteen.view.Screen

@Composable
fun RegistrationScreen(email: String = "", viewModel: RegistrationViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(RegistrationEvent.OnStartScreen(email))
    }

    Screen(viewModel = viewModel) { state, action, navController ->
        RegistrationView(state) { event -> viewModel.obtainEvent(event) }
        when (action) {
            RegistrationAction.GoToStack -> navController.popBackStack()
            RegistrationAction.OpenSuccessRegistration -> {
                SuccessRegistrationDialog { event ->
                    viewModel.obtainEvent(event)
                }
            }

            null -> {}
            RegistrationAction.OpenStartScreen -> navController.push(
                NavigationTree.SignIn,
                launchFlag = LaunchFlag.ClearNavGraph
            )
        }
    }
}


