package ru.sr.nineteen.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.theme.GameTheme


@SuppressLint("MissingColorAlphaChannel")
@Composable
fun ActionButtonView(
    text: String,
    enabled: Boolean = true,
    padding: PaddingValues = PaddingValues(),
    containerColor: Color = GameTheme.colors.blue_400,
    contentColor: Color = GameTheme.colors.text,
    borderColor: Color = GameTheme.colors.blue_500,
    isOutLine: Boolean = false,
    style : TextStyle =  GameTheme.fonts.h3
        .copy(color = if (isOutLine) GameTheme.colors.textTitle else GameTheme.colors.textButton),
    onClick: () -> Unit,
) {


    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = if (isOutLine) Color(0xFCFCFC) else containerColor,
        contentColor = contentColor,
    )
    Button(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(),
        colors = buttonColors,
        shape = GameTheme.shapes.small,
        enabled = enabled,
        border = if (!isOutLine) null else BorderStroke(width = 1.dp, color = borderColor),
        onClick = { onClick() },
    ) {
        Text(
            text = text,
            style = style
        )
    }
}