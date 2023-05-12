package ru.sr.nineteen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.data.database.entity.RatingEntity
import ru.sr.nineteen.rating.R
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.Screen
import ru.sr.nineteen.viewmodel.RatingAction
import ru.sr.nineteen.viewmodel.RatingEvent
import ru.sr.nineteen.viewmodel.RatingState
import ru.sr.nineteen.viewmodel.RatingViewModel

@Composable
fun RatingScreen(viewModel: RatingViewModel = koinViewModel()) {
    Screen(viewModel = viewModel) { state, action, navController ->
        LaunchedEffect(key1 = true) {
            viewModel.obtainEvent(RatingEvent.OnStartScreen)
        }
        RatingView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            RatingAction.GoToBack -> {
                navController.popBackStack()
            }
            null -> {}
        }

    }
}

@Composable
fun RatingView(state: RatingState, eventHandler: (RatingEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        RatingTitle()
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(state.ratingItems.size) { position ->
                RatingItem(state.ratingItems[position], position)
            }
        }
        ActionButtonView(text = "Назад") {
            eventHandler(RatingEvent.OnClickBackStack)
        }
    }

}

@Composable
fun RatingItem(ratingItem: RatingEntity, position: Int) {

    val color = if (position % 2 == 0) GameTheme.colors.blue_100 else GameTheme.colors.blue_300

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = ratingItem.id.toString()
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = ratingItem.time.toString()
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = ratingItem.stepCount.toString()
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = ratingItem.gameMode
        )

    }
}

@Composable
fun RatingTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.rating_position_title)
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.rating_time_title)
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.rating_step_title)
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.rating_mode_title)
        )

    }
}
