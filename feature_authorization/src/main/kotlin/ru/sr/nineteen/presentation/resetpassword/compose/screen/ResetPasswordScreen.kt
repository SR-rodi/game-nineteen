package ru.sr.nineteen.presentation.resetpassword.compose.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.navgraph.navcomponent.LaunchFlag
import ru.sr.nineteen.navgraph.navcomponent.push
import ru.sr.nineteen.presentation.resetpassword.compose.view.ResetPasswordView
import ru.sr.nineteen.presentation.resetpassword.viewmodel.ResetPasswordViewModel
import ru.sr.nineteen.presentation.resetpassword.viewmodel.model.ResetPasswordAction
import ru.sr.nineteen.presentation.resetpassword.viewmodel.model.ResetPasswordEvent
import ru.sr.nineteen.view.Screen

@Composable
fun ResetPasswordScreen(email: String = "", viewModel: ResetPasswordViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(ResetPasswordEvent.OnChangeEmail(email))
    }

    Screen(viewModel = viewModel) { state, action, navController ->

        ResetPasswordView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            ResetPasswordAction.OpenResetDialog -> {
                SuccessSendMailResetPasswordDialog { event -> viewModel.obtainEvent(event) }
            }

            ResetPasswordAction.OpenSignInScreen ->
                navController.push(
                    NavigationTree.SignIn, LaunchFlag.ClearNavGraph
                )

            null -> {}
        }
    }
}

