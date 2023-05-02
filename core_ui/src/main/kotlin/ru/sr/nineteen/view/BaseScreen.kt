package ru.sr.nineteen.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.sr.nineteen.BaseViewModel

@Composable
fun <State : Any, Action : Any, Event : Any> Screen(
    viewModel: BaseViewModel<State, Action, Event>,
    content: @Composable (state: State, action: Action?, rootController: RootController) -> Unit,
) {
    val rootController = LocalRootController.current
    val state = viewModel.viewStates().collectAsState()
    val action = viewModel.viewActions().collectAsState(initial = null)
    content(state.value, action.value, rootController)
}