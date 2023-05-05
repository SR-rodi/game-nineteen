package ru.sr.nineteen.presentation.compose.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem
import ru.sr.nineteen.theme.GameTheme

@Composable
fun GameItemView(item: GameItemEngine, evenHandler: () -> Unit) {

    var cardColor by remember { mutableStateOf(Color(0xFF9CB9D1)) }
    var borderColor by remember { mutableStateOf(Color(0xFF9CB9D1)) }

    var modifier: Modifier by remember { mutableStateOf(Modifier.clickable { evenHandler() }) }

    when (item.statusItem) {
        StatusItem.CHOICE -> {
            modifier = Modifier
            cardColor = GameTheme.colors.choice
            borderColor = GameTheme.colors.choice
        }
        StatusItem.NOT_CHOICE -> {
            cardColor = GameTheme.colors.notChoice
            borderColor = GameTheme.colors.notChoice
        }
        StatusItem.SELECT -> {
            cardColor = GameTheme.colors.select
            borderColor = GameTheme.colors.select
        }
        StatusItem.HELP -> {
            cardColor = GameTheme.colors.notChoice
            borderColor = GameTheme.colors.help
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = GameTheme.shapes.small)
         .padding(4.dp)
            .then(modifier),
        shape = GameTheme.shapes.small,
        border = BorderStroke(width = 2.dp, color = borderColor),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            textAlign = TextAlign.Center,
            text = item.number.toString(),
            style = GameTheme.fonts.h2.copy(color = GameTheme.colors.textTitle)
        )
    }
}