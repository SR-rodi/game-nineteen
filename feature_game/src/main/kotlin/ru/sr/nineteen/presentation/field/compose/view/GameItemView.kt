package ru.sr.nineteen.presentation.field.compose.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
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
    rotate: Float = 0F,
    scale: Float = 1F,
    onClickItem: () -> Unit = {},
) {
    val modifier =
        if (borderColor == null) Modifier else Modifier.border(
            width = 2.dp,
            color = borderColor,
            shape = GameTheme.shapes.small
        )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = GameTheme.shapes.small)
            .padding(4.dp)
            .rotate(rotate)
            .scale(scale)
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

    val transition = rememberInfiniteTransition()
    val rotate by transition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(500,easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val scale by transition.animateFloat(
        initialValue = 1F,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


    BaseGameItem(
        item = item,
        cardColor = cardColor,
        onClickItem = onClickItem,
        borderColor = borderColor,
        rotate = rotate,
        scale = scale
    )
}