package ru.sr.nineteen.presentation.win.compose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.game.R
import ru.sr.nineteen.theme.GameTheme

@Composable
fun WinCardView(settingGame: SettingGame) {

    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
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
        Text(
            text = stringResource(id = R.string.game_win_result),
            color = GameTheme.colors.textTitle
        )
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