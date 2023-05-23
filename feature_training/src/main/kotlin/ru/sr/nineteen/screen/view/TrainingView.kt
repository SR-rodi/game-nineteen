package ru.sr.nineteen.screen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.composeview.ActionButtonView
import ru.sr.nineteen.composeview.GameFieldView
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.training.R
import ru.sr.nineteen.viewmodel.model.TrainingEvent
import ru.sr.nineteen.viewmodel.model.TrainingState

@Composable
fun TrainingView(state: TrainingState, onClickButton: (TrainingEvent) -> Unit) {

    var screenNumber by remember { mutableStateOf(0) }

    val descriptions = stringArrayResource(id = R.array.learn)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(GameTheme.shapes.large)
                .background(GameTheme.colors.blue_100)

        ) {

            Text(
                modifier = Modifier.padding(8.dp),
                text = descriptions[screenNumber]
            )

            GameFieldView(items = state.items, contentPadding = PaddingValues(16.dp)) {}

            Row(modifier = Modifier.padding(16.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    ActionButtonView(
                        text = "Назад",
                        style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textTitle),
                        isOutLine = true
                    ) {
                        onClickButton(TrainingEvent.OnClickSkipButton)
                    }
                }
                Spacer(modifier = Modifier.size(8.dp))
                Box(modifier = Modifier.weight(1f)) {
                    ActionButtonView(
                        text = "Продолжить",
                        style = GameTheme.fonts.h4.copy(color = GameTheme.colors.textTitle),
                        enabled = screenNumber != 5,
                        isOutLine = true
                    ) {
                        screenNumber++
                        if (screenNumber <= 4)
                            onClickButton(TrainingEvent.OnClickNextButton(screenNumber))
                    }
                }
            }
        }
    }
}