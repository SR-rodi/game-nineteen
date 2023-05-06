package ru.sr.nineteen.presentation.registration.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationAction
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationEvent
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationState
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationViewModel
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
            RegistrationAction.OpenSuccessRegistration -> rootController.popBackStack()
            null -> {}
        }
    }
}

@Composable
fun RegistrationView(state: RegistrationState, eventHandler: (RegistrationEvent) -> Unit) {

    Text(text = state.email)
}
