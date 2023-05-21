package ru.sr.nineteen.presentation.compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.theme.GameTheme


@Composable
fun LeaderTableView(
    tableName: String = "Таблица лидеров",
    borderColor: Color = GameTheme.colors.blue_100,
    widthBorder: Dp = 2.dp,
    textStyle: TextStyle = GameTheme.fonts.h2.copy(color = GameTheme.colors.blue_100),
    titleBackground: Color = GameTheme.colors.blue_400,
    tableBackground: Color = GameTheme.colors.blue_400,
    titleSize: Dp = 60.dp,
    shape: Shape = GameTheme.shapes.small,
    content: @Composable ColumnScope.() -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = titleSize / 2)
                .background(tableBackground, shape = GameTheme.shapes.small)
                .clip(shape = GameTheme.shapes.small)
                .border(width = widthBorder, color = borderColor, shape = shape)
        ) {
            Spacer(modifier = Modifier.size(titleSize / 2))
            content()
        }
        Text(
            modifier = Modifier
                .height(titleSize)
                .border(width = widthBorder, color = borderColor)
                .background(titleBackground)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            style = textStyle,
            text = tableName,
        )

    }
}