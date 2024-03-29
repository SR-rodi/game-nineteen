package ru.sr.nineteen.presentation.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.theme.GameTheme

@Composable
fun ErrorMessageView(
    isVisible: Boolean,
    message: String,
    isClickable: Boolean = false,
    onClick: () -> Unit = {},
) {
    AnimatedVisibility(visible = isVisible) {
        val clickModifier = if (isClickable) Modifier.clickable { onClick() } else Modifier
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .then(clickModifier),
            text = message,
            style = GameTheme.fonts.p.copy(GameTheme.colors.error),
            textAlign = TextAlign.Center
        )
    }
}