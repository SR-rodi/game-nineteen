package ru.sr.nineteen.presentation.compose

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.navgraph.navcomponent.push
import ru.sr.nineteen.presentation.compose.view.MenuView
import ru.sr.nineteen.presentation.viewmodel.MenuViewModel
import ru.sr.nineteen.presentation.viewmodel.model.MenuAction
import ru.sr.nineteen.presentation.viewmodel.model.MenuEvent
import ru.sr.nineteen.composeview.Screen

@Composable
fun MenuScreen(viewModel: MenuViewModel = koinViewModel()) {

    Screen(viewModel = viewModel) { state, action, navController ->
        MenuView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            is MenuAction.OpenGame -> {
                navController.push(NavigationTree.Game, action.gameMode)
                viewModel.obtainEvent(MenuEvent.ResetActions)
            }

            MenuAction.OpenRating -> {
                navController.push(NavigationTree.Rating)
                viewModel.obtainEvent(MenuEvent.ResetActions)
            }

            MenuAction.OpenTraining -> {
                navController.push(NavigationTree.Training)
                viewModel.obtainEvent(MenuEvent.ResetActions)
            }

            MenuAction.OpenProfile -> {
                navController.push(NavigationTree.Profile)
                viewModel.obtainEvent(MenuEvent.ResetActions)
            }

            null -> {}

        }
    }
}