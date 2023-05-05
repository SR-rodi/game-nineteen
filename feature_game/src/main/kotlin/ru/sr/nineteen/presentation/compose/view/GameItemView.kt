package ru.sr.nineteen.presentation.compose.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.theme.GameTheme

@Composable
fun GameItemChoiceView(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.choice,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = GameTheme.shapes.small)
            .padding(4.dp),
        shape = GameTheme.shapes.small,
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

@Composable
fun GameItemNotChoiceView(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.notChoice,
    onClickItem: () -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = GameTheme.shapes.small)
            .padding(4.dp)
            .clickable { onClickItem() },
        shape = GameTheme.shapes.small,
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

@Composable
fun GameItemSelectView(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.select,
    onClickItem: () -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = GameTheme.shapes.small)
            .padding(4.dp)
            .clickable { onClickItem() },
        shape = GameTheme.shapes.small,
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


@Composable
fun GameItemHelpView(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.notChoice,
    borderColor: Color = GameTheme.colors.help,
    onClickItem: () -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = GameTheme.shapes.small)
            .padding(4.dp)
            .border(width = 1.dp, color = borderColor)
            .clickable { onClickItem() },
        shape = GameTheme.shapes.small,
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

