package ru.sr.nineteen.presentation.win.compose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.game.R
import ru.sr.nineteen.presentation.win.viewmodel.model.WinEvent
import ru.sr.nineteen.presentation.win.viewmodel.model.WinState
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView

@Composable
fun WinView(settingGame: WinState, evenHandler: (WinEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.fillMaxWidth()) {
            WinCardView(settingGame)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Box(modifier = Modifier.weight(1f)) {
                    ActionButtonView(
                        text = stringResource(id = R.string.game_rating_button),
                        padding = PaddingValues()
                    ) {
                        evenHandler(WinEvent.OnRatingButtonClick)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.weight(1f)) {
                    ActionButtonView(
                        text = stringResource(id = R.string.game_menu_button),
                        padding = PaddingValues()
                    ) {
                        evenHandler(WinEvent.OnMenuButtonClick)
                    }
                }
            }
        }
    }
}

@Composable
fun WinCardView(settingGame: WinState) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = GameTheme.fonts.h1.copy(color = GameTheme.colors.textTitle),
                text = stringResource(id = R.string.game_win_message)
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = GameTheme.fonts.h3.copy(color = GameTheme.colors.textTitle),
                text = settingGame.gameMode
            )
            Text(text = stringResource(id = R.string.game_win_result))
            Text(text = ("${stringResource(id = R.string.game_counter_steps)}  ${settingGame.stepCount}"))
            Text(text = ("${stringResource(id = R.string.game_time)}  ${settingGame.time}"))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp, max = 80.dp)
                    .padding(8.dp),
                painter = painterResource(id = ru.sr.nineteen.core_ui.R.drawable.kitekat_1),
                contentDescription = "win Image"
            )
        }

    }

}