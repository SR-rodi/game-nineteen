package ru.sr.nineteen.presentation.registration.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.core.LaunchFlag
import ru.sr.nineteen.authorization.R
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.registration.compose.view.RegistrationView
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationAction
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationEvent
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationViewModel
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.utils.presentAlertDialog
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.Screen

@Composable
fun RegistrationScreen(email: String = "", viewModel: RegistrationViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(RegistrationEvent.OnStartScreen(email))
    }

    Screen(viewModel = viewModel) { state, action, rootController ->
        RegistrationView(state) { event -> viewModel.obtainEvent(event) }
        when (action) {
            RegistrationAction.GoToStack -> rootController.popBackStack()
            RegistrationAction.OpenSuccessRegistration -> {
                rootController.findModalController()
                    .presentAlertDialog(closeOnBackdropClick = false) {
                        SuccessRegistrationView(rootController)
                    }
                viewModel.obtainEvent(RegistrationEvent.OnResetAction)
            }

            null -> {}
        }
    }
}

@Composable
fun SuccessRegistrationView(rootController: RootController) {
    Column(Modifier.padding(16.dp)) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.auth_send_reset_password_success_title),
            style = GameTheme.fonts.h2.copy(GameTheme.colors.textTitle),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.auth_registration_success_message),
            style = GameTheme.fonts.h4.copy(GameTheme.colors.text)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ActionButtonView(
            text = stringResource(id = R.string.auth_registration_success_Button),
            padding = PaddingValues()
        ) {
            rootController.push(NavigationTree.SignIn.name, launchFlag = LaunchFlag.SingleNewTask)
        }
    }
}
