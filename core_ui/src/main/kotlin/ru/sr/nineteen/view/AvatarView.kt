package ru.sr.nineteen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.sr.nineteen.theme.GameShapes
import ru.sr.nineteen.theme.GameTheme

@Composable
fun AvatarView(
    image: Any,
    padding: PaddingValues = PaddingValues(top = 50.dp),
    widthBorder: Dp = 2.dp,
    size: Dp = 100.dp,
    borderColor: Color = GameTheme.colors.blue_100,
    shapes: Shape = GameTheme.shapes.large,
    onClick: () -> Unit = {},
) {
    Box(modifier = Modifier.padding(padding).clip(shapes).background(borderColor)){
        AsyncImage(
            modifier = Modifier
                .size(size)
                .clip(shapes)
                .clickable { onClick() }
                .border(width = widthBorder, color = borderColor, shapes),

            model = image,
            contentDescription = ""
        )
    }


}