package ru.sr.nineteen.presentation.signin.compose.screen

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.core.LaunchFlag
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.signin.compose.view.SignInView
import ru.sr.nineteen.presentation.signin.viewmodel.SignInViewModel
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInAction
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInEvent
import ru.sr.nineteen.utils.presentAlertDialog
import ru.sr.nineteen.view.Screen

@Composable
fun SignInScreen(viewModel: SignInViewModel = koinViewModel()) {
    Screen(viewModel = viewModel) { state, action, rootController ->
        SignInView(state) { event -> viewModel.obtainEvent(event) }
        when (action) {
            is SignInAction.OpenMenu -> {
                rootController.push(NavigationTree.Menu.name, launchFlag = LaunchFlag.ClearPrevious)
            }

            is SignInAction.OpenRegistration -> {
                rootController.push(NavigationTree.Registration.name, params = action.email)
                viewModel.obtainEvent(SignInEvent.OnResetAction)
            }

            is SignInAction.OpenResetPassword -> {
                rootController.push(NavigationTree.ResetPassword.name, params = action.email)
                viewModel.obtainEvent(SignInEvent.OnResetAction)
            }

            SignInAction.OpenWarningMessage -> {
                rootController.findModalController().presentAlertDialog {
                        WarningNotAuthScreen(rootController = rootController)
                    }
                viewModel.obtainEvent(SignInEvent.OnResetAction)
            }
            null -> {}

        }
    }
}
