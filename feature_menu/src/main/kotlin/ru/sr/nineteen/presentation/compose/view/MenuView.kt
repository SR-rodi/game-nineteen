package ru.sr.nineteen.presentation.compose.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.core_ui.R
import ru.sr.nineteen.presentation.viewmodel.model.MenuEvent
import ru.sr.nineteen.presentation.viewmodel.model.MenuState

@Composable
fun MenuView(state: MenuState, eventHandler: (event: MenuEvent) -> Unit) {

    LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
        item {
            ItemMenuView(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = ru.sr.nineteen.menu.R.string.menu_next_title),
                textButton = stringResource(id = ru.sr.nineteen.menu.R.string.menu_game_button),
                imageID = R.drawable.kitekat_1,
                isEnable = state.isNextEnable
            ) { eventHandler(MenuEvent.OnClickNextButton) }
        }
        item {
            Row {
                ItemMenuView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = stringResource(id = ru.sr.nineteen.menu.R.string.menu_classic_title),
                    textButton = stringResource(id = ru.sr.nineteen.menu.R.string.menu_game_button),
                    imageID = R.drawable.kitekat_1
                ) {eventHandler(MenuEvent.OnClickClassicButton)}
                Spacer(modifier = Modifier.width(8.dp))
                ItemMenuView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = stringResource(id = ru.sr.nineteen.menu.R.string.menu_random_title),
                    textButton = stringResource(id = ru.sr.nineteen.menu.R.string.menu_game_button),
                    imageID = R.drawable.kitekat_1
                ) {eventHandler(MenuEvent.OnClickRandomButton)}
            }
        }
        item {
            Row {
                ItemMenuView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = stringResource(id = ru.sr.nineteen.menu.R.string.menu_training_title),
                    textButton = stringResource(id = ru.sr.nineteen.menu.R.string.menu_go_button),
                    imageID = R.drawable.kitekat_1
                ) {eventHandler(MenuEvent.OnClickTrainingButton)}
                Spacer(modifier = Modifier.width(8.dp))
                ItemMenuView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = stringResource(id = ru.sr.nineteen.menu.R.string.menu_rating_title),
                    textButton = stringResource(id = ru.sr.nineteen.menu.R.string.menu_look_button),
                    imageID = R.drawable.kitekat_1
                ) {eventHandler(MenuEvent.OnClickRatingButton)}
            }
        }
    }
}
