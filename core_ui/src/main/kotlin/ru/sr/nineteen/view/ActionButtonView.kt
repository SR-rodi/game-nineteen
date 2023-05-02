package ru.sr.nineteen.view

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
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.theme.GameTheme

@Composable
fun ActionButtonView(
    text: String,
    enabled: Boolean = true,
    padding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp),
    containerColor: Color = GameTheme.colors.blue_400,
    contentColor:Color = GameTheme.colors.text,
    onClick: () -> Unit,
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    Button(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(),
        colors = buttonColors,
        shape = GameTheme.shapes.small,
        enabled = enabled,
        onClick = { onClick() },
    ) {
        Text(
            text = text,
            style = GameTheme.fonts.h3.copy(color = GameTheme.colors.textButton)
        )
    }
}