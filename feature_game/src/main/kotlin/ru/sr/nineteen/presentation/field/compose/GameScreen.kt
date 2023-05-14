package ru.sr.nineteen.presentation.field.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.composeview.Screen
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.gameitem.GameMode
import ru.sr.nineteen.navgraph.navcomponent.LaunchFlag
import ru.sr.nineteen.navgraph.navcomponent.push
import ru.sr.nineteen.presentation.field.compose.view.GameView
import ru.sr.nineteen.presentation.field.viewmodel.GameViewModel
import ru.sr.nineteen.presentation.field.viewmodel.model.GameAction
import ru.sr.nineteen.presentation.field.viewmodel.model.GameEvent
import ru.sr.nineteen.presentation.win.compose.WinDialog

@Composable
fun GameScreen(gameMode: GameMode.Game, viewModel: GameViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(GameEvent.OnStartGame(gameMode))
    }
    Screen(viewModel = viewModel) { state, action, navController ->

        GameView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            GameAction.GoToBackStack -> {
                navController.popBackStack()
            }

            is GameAction.OpenWinScreen -> {
                WinDialog(settingGame = action.setting) { event -> viewModel.obtainEvent(event) }
            }

            GameAction.SaveWinIfo -> {
                viewModel.obtainEvent(GameEvent.OnWinOpen)
            }

            GameAction.OpenRating -> {
                navController.push(NavigationTree.Rating, launchFlag = LaunchFlag.ClearPrevious)
                viewModel.obtainEvent(GameEvent.OnResetActions)
            }

            null -> {}

        }

        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(lifecycleOwner) {
            onDispose {
                viewModel.obtainEvent(GameEvent.OnDispose)
            }
        }
    }
}



