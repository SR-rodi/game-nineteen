package ru.sr.nineteen.presentation.win.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.game.R
import ru.sr.nineteen.presentation.field.viewmodel.model.GameEvent
import ru.sr.nineteen.presentation.win.compose.view.WinCardView
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.GameDialog

@Composable
fun WinDialog(
    settingGame: SettingGame,
    eventHandler: (GameEvent) -> Unit,
) {

    GameDialog() {
        Box(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(Modifier.fillMaxWidth()) {
                WinCardView(settingGame)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Box(modifier = Modifier.weight(1f)) {
                        ActionButtonView(
                            style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textButton),
                            text = stringResource(id = R.string.game_rating_button),
                        ) {
                            eventHandler(GameEvent.OnClickRatingWithDialog)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        ActionButtonView(
                            text = stringResource(id = R.string.game_menu_button),
                            style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textButton),
                        ) {
                            eventHandler(GameEvent.OnClickMenuWithDialog)
                        }
                    }
                }
            }
        }
    }

}

