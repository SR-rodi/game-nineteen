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
fun BaseGameItem(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.notChoice,
    borderColor: Color? = null,
    onClickItem: () -> Unit = {},
) {
    val modifier =
        if (borderColor == null) Modifier else Modifier.border(width = 2.dp, color = borderColor)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = GameTheme.shapes.small)
            .padding(4.dp)
            .then(modifier)
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
fun GameItemChoiceView(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.choice,
) {
    BaseGameItem(item = item, cardColor = cardColor)
}

@Composable
fun GameItemNotChoiceView(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.notChoice,
    onClickItem: () -> Unit,
) {
    BaseGameItem(item = item, cardColor = cardColor, onClickItem = onClickItem)

}

@Composable
fun GameItemSelectView(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.select,
    onClickItem: () -> Unit,
) {

    BaseGameItem(item = item, cardColor = cardColor, onClickItem = onClickItem)
}


@Composable
fun GameItemHelpView(
    item: GameItemEngine,
    cardColor: Color = GameTheme.colors.notChoice,
    borderColor: Color = GameTheme.colors.help,
    onClickItem: () -> Unit,
) {

    BaseGameItem(
        item = item,
        cardColor = cardColor,
        onClickItem = onClickItem,
        borderColor = borderColor
    )
}