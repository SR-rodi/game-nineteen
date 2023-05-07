package ru.sr.nineteen.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.RootController
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.sr.nineteen.BaseViewModel
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.compose.view.MenuView
import ru.sr.nineteen.presentation.viewmodel.MenuViewModel
import ru.sr.nineteen.presentation.viewmodel.model.MenuAction
import ru.sr.nineteen.presentation.viewmodel.model.MenuEvent
import ru.sr.nineteen.view.Screen

@Composable
fun MenuScreen(viewModel: MenuViewModel = koinViewModel()) {



    Screen(viewModel = viewModel) { state, action, rootController ->
        MenuView(state) { event -> viewModel.obtainEvent(event) }
        when (action) {
            is MenuAction.OpenGame -> {
                rootController.push(NavigationTree.Game.name,action.settingGame)
                viewModel.obtainEvent(MenuEvent.ResetActions)
            }

            MenuAction.OpenRating -> {
                rootController.push(NavigationTree.Rating.name)
                viewModel.obtainEvent(MenuEvent.ResetActions)
            }

            MenuAction.OpenTraining -> {
                rootController.push(NavigationTree.Training.name)
                viewModel.obtainEvent(MenuEvent.ResetActions)
            }

            null -> {}
        }
    }
}