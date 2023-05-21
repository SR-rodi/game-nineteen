package ru.sr.nineteen.composeview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.sr.nineteen.theme.GameTheme

@Composable
fun GameDialog(
    modifier: Modifier = Modifier,
    shape: Shape = GameTheme.shapes.dialog,
    onDismiss: () -> Unit = {},
    background: Color = GameTheme.colors.blue_100,
    dismissOnClickOutside: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {


    Dialog(
        properties = DialogProperties(dismissOnClickOutside = dismissOnClickOutside),
        onDismissRequest = onDismiss
    ) {
        Box(
            Modifier
                .clip(shape = shape)
                .background(background)
                .then(modifier),
            content = content
        )

    }

}