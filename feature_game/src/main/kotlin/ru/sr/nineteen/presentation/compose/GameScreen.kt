package ru.sr.nineteen.presentation.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.core_ui.R
import ru.sr.nineteen.domain.gameitem.Position
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.presentation.compose.view.ItemsLine
import ru.sr.nineteen.presentation.viewmodel.GameViewModel
import ru.sr.nineteen.presentation.viewmodel.model.GameAction
import ru.sr.nineteen.presentation.viewmodel.model.GameEvent
import ru.sr.nineteen.presentation.viewmodel.model.GameState
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.Screen

@Composable
fun GameScreen(settingGame: SettingGame, viewModel: GameViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.obtainEvent(GameEvent.OnStartGame(settingGame))
    }
    Screen(viewModel = viewModel) { state, action, rootController ->
        GameFieldView(state) { viewModel.obtainEvent(it) }

        when (action) {
            GameAction.GoToBackStack -> {
                rootController.popBackStack()
                viewModel.obtainEvent(GameEvent.ResetActions)
            }

            null -> {}
            GameAction.OpenWinScreen -> {
                Log.e("Kart", "WIN")
            }
        }

        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(lifecycleOwner) {
            onDispose {
                viewModel.obtainEvent(GameEvent.OnDispose)
            }
        }
    }
}

@Composable
fun GameFieldView(settingGame: GameState, evenHandler: (GameEvent) -> Unit) {

    if (settingGame.isWin) evenHandler(GameEvent.OnWinOpen)

    Column {
        GameTitle(settingGame.mode, evenHandler)
        GameStatisticView(settingGame)

        LazyColumn(modifier = Modifier.weight(1f), contentPadding = PaddingValues(4.dp)) {
            itemsIndexed(settingGame.items) { rowId, list ->
                Row(modifier = Modifier.fillMaxSize()) {

                    ItemsLine(items = list) {columnId->
                        evenHandler(GameEvent.OnClickItem(Position(rowId, columnId)))
                    }
/*                    list.forEachIndexed { columnId, item ->
                        Box(modifier = Modifier.weight(1f)) {
                            GameItemView(item = item) {
                                evenHandler(
                                    GameEvent.OnClickItem(Position(rowId, columnId))
                                )
                            }
                        }
                    }*/

                    if (list.size < 9) {
                        for (i in list.size..8) {
                            Box(modifier = Modifier.weight(1f))
                        }
                    }

                }
            }
        }
        GameButtonView(evenHandler)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun GameTitle(title: String, evenHandler: (GameEvent) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clickable { evenHandler(GameEvent.OnClickBackArrow) },
            painter = painterResource(id = R.drawable.core_ui_arrow_back),
            contentDescription = "back"
        )
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = GameTheme.fonts.h3.copy(color = GameTheme.colors.textButton),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GameStatisticView(settingGame: GameState) {
    Row(modifier = Modifier.padding(horizontal = 8.dp)) {
        val style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textButton)
        Text(
            text = stringResource(id = ru.sr.nineteen.game.R.string.game_counter_steps),
            style = style
        )
        Text(text = settingGame.stepCounter.toString(), style = style)
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            text = settingGame.timeCounter.toString(),
            style = style
        )
    }
}

@Composable
fun GameButtonView(evenHandler: (GameEvent) -> Unit) {
    Row(modifier = Modifier.padding(horizontal = 8.dp)) {
        Box(Modifier.weight(1f)) {
            ActionButtonView(
                padding = PaddingValues(),
                text = stringResource(id = ru.sr.nineteen.game.R.string.game_add_button)
            ) { evenHandler(GameEvent.OnClickAddButton) }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(Modifier.weight(1f)) {
            ActionButtonView(
                padding = PaddingValues(),
                text = stringResource(id = ru.sr.nineteen.game.R.string.game_help_button)
            ) { evenHandler(GameEvent.OnClickHelpButton) }
        }

    }
}