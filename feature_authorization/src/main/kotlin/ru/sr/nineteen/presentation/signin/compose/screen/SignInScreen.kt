package ru.sr.nineteen.presentation.signin.compose.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.signin.viewmodel.AuthAction
import ru.sr.nineteen.presentation.signin.viewmodel.AuthState
import ru.sr.nineteen.presentation.signin.viewmodel.SignInViewModel
import ru.sr.nineteen.view.Screen

@Composable
fun SignInScreen(viewModel: SignInViewModel = koinViewModel()) {
    Screen(viewModel = viewModel) { state, action, rootController ->
        SignInView(state)
        Text(text = "AuthScreen")
        when (action) {
            is AuthAction.OpenMenu -> rootController.push(NavigationTree.Menu.name)
            is AuthAction.OpenRegistration -> rootController.push(NavigationTree.Registration.name)
            null -> {}
        }
    }
}

@Composable
fun SignInView(state: AuthState) {

}