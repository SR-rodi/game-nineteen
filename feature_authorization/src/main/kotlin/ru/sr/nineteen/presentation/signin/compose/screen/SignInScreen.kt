package ru.sr.nineteen.presentation.signin.compose.screen

import android.util.Log
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.signin.compose.view.SignInView
import ru.sr.nineteen.presentation.signin.viewmodel.SignInAction
import ru.sr.nineteen.presentation.signin.viewmodel.SignInViewModel
import ru.sr.nineteen.view.Screen

@Composable
fun SignInScreen(viewModel: SignInViewModel = koinViewModel()) {
    Screen(viewModel = viewModel) { state, action, rootController ->
        SignInView(state) { event -> viewModel.obtainEvent(event) }
        when (action) {
            is SignInAction.OpenMenu -> rootController.push(NavigationTree.Menu.name)
            is SignInAction.OpenRegistration -> rootController.push(NavigationTree.Registration.name)
            null -> {}
            SignInAction.OpenResetPassword -> {
                Log.e("Kart","OpenResetPassword")
            }
        }
    }
}