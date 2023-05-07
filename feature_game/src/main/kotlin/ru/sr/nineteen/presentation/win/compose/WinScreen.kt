package ru.sr.nineteen.presentation.win.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.core.LaunchFlag
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.presentation.field.viewmodel.model.GameEvent
import ru.sr.nineteen.presentation.win.compose.view.WinView
import ru.sr.nineteen.presentation.win.viewmodel.model.WinAction
import ru.sr.nineteen.presentation.win.viewmodel.model.WinEvent
import ru.sr.nineteen.presentation.win.viewmodel.WinViewModel
import ru.sr.nineteen.view.Screen

@Composable
fun WinScreen(settingGame: SettingGame, viewModel: WinViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(WinEvent.OnStartScreen(settingGame))
    }

    Screen(viewModel = viewModel) { state, action, rootController ->
        WinView(settingGame = state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            WinAction.OpenMenu -> {
                viewModel.obtainEvent(WinEvent.OnResetAction)
                rootController.popBackStack()
            }

            WinAction.OpenRating -> {
                viewModel.obtainEvent(WinEvent.OnResetAction)
                rootController.push(NavigationTree.Rating.name, LaunchFlag.ClearPrevious)
            }

            null -> {}
        }
    }
}

