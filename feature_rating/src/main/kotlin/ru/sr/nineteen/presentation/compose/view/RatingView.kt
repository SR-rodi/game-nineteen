package ru.sr.nineteen.presentation.compose.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.composeview.ActionButtonView
import ru.sr.nineteen.composeview.BaseProgressIndicator
import ru.sr.nineteen.presentation.viewmodel.model.RatingEvent
import ru.sr.nineteen.presentation.viewmodel.model.RatingState
import ru.sr.nineteen.rating.R

@Composable
fun RatingView(state: RatingState, eventHandler: (RatingEvent) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        LeaderTableView {

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BaseProgressIndicator(
                    isVisible = state.isLoading,
                    modifier = Modifier.padding(16.dp)
                )
            }

            AnimatedVisibility(visible = !state.isLoading) {
                LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                    itemsIndexed(state.ratingItems) { index, item ->
                        RatingItem(item, index)
                    }

                    item {
                        if (state.myRating != null) {
                            Spacer(modifier = Modifier.size(16.dp))
                            Log.e("Kart", "position = ${state.myPosition}")
                            RatingItem(state.myRating, state.myPosition!!)
                        }
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

        ActionButtonView(text = "Назад") {
            eventHandler(RatingEvent.OnClickBackStack)
        }
    }
}