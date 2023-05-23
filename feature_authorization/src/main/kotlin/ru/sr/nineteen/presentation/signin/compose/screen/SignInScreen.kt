package ru.sr.nineteen.presentation.signin.compose.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.navgraph.navcomponent.push
import ru.sr.nineteen.presentation.signin.compose.view.SignInView
import ru.sr.nineteen.presentation.signin.viewmodel.SignInViewModel
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInAction
import ru.sr.nineteen.presentation.signin.viewmodel.model.SignInEvent
import ru.sr.nineteen.composeview.Screen
import ru.sr.nineteen.composeview.ShowToast
import ru.sr.nineteen.navgraph.navcomponent.LaunchFlag
import ru.sr.nineteen.presentation.signin.compose.view.WarningNotAuthDialog

@Composable
fun SignInScreen(viewModel: SignInViewModel = koinViewModel()) {
    Screen(viewModel = viewModel) { state, action, navController ->
        SignInView(state) { event -> viewModel.obtainEvent(event) }
        when (action) {
            is SignInAction.OpenMenu -> {
                navController.push(NavigationTree.Menu, LaunchFlag.ClearPrevious)
            }

            is SignInAction.OpenRegistration -> {
                navController.push(route = NavigationTree.Registration, params = action.email)
                viewModel.obtainEvent(SignInEvent.OnResetAction)
            }

            is SignInAction.OpenResetPassword -> {
                navController.push(route = NavigationTree.ResetPassword, params = action.email)
                viewModel.obtainEvent(SignInEvent.OnResetAction)
            }

            SignInAction.OpenWarningMessage -> {
                WarningNotAuthDialog { event -> viewModel.obtainEvent(event) }
            }

            SignInAction.ShowToastSuccessSendEmail -> ShowToast(message = stringResource(id = R.string.auth_send_reset_password_success_message))
            null -> {}

        }
    }
}
