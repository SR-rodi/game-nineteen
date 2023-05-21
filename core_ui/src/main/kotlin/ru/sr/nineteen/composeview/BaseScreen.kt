package ru.sr.nineteen.composeview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.theme.LocalRootController

@Composable
fun <State : Any, Action : Any, Event : Any> Screen(
    viewModel: BaseViewModel<State, Action, Event>,
    content: @Composable (state: State, action: Action?, navController: NavHostController) -> Unit,
) {
    val state = viewModel.viewStates().collectAsState()
    val action = viewModel.viewActions().collectAsState(initial = null)
    content(state.value, action.value, LocalRootController.current)
}