package ru.sr.nineteen.presentation.compose

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.navgraph.navcomponent.push
import ru.sr.nineteen.presentation.compose.view.MenuView
import ru.sr.nineteen.presentation.viewmodel.MenuViewModel
import ru.sr.nineteen.presentation.viewmodel.model.MenuAction
import ru.sr.nineteen.presentation.viewmodel.model.MenuEvent
import ru.sr.nineteen.view.Screen

fun NavController.navigate(
    route: NavigationTree,
    params: Pair<String, SettingGame>,
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    this.currentBackStackEntry?.savedStateHandle?.set(
        key = params.first,
        value = params.second
    )
    navigate(route.name, builder)
}

@Composable
fun MenuScreen(viewModel: MenuViewModel = koinViewModel()) {

    Screen(viewModel = viewModel) { state, action, navController ->
        MenuView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            is MenuAction.OpenGame -> {
                navController.push(NavigationTree.Game, action.settingGame)
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