package ru.sr.nineteen.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.presentation.compose.view.LeaderTableView
import ru.sr.nineteen.presentation.compose.view.RatingItem
import ru.sr.nineteen.presentation.viewmodel.RatingAction
import ru.sr.nineteen.presentation.viewmodel.RatingEvent
import ru.sr.nineteen.presentation.viewmodel.RatingState
import ru.sr.nineteen.presentation.viewmodel.RatingViewModel
import ru.sr.nineteen.rating.R
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.BaseProgressIndicator
import ru.sr.nineteen.view.Screen

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
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        LeaderTableView {

            BaseProgressIndicator(isVisible = state.isLoading)
            AnimatedVisibility(visible = !state.isLoading) {
                LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                    itemsIndexed(state.ratingItems) { index, item ->
                        RatingItem(item, index)
                    }

                    item {
                        ActionButtonView(
                            isOutLine = true,
                            padding = PaddingValues(vertical = 8.dp),
                            text = stringResource(id = R.string.rating_show_my_result_button)
                        ) {
                            eventHandler(RatingEvent.OnClickShowMyResultButton)
                        }
                    }
                }

            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        ActionButtonView(text = "Назад") {}
    }
}