package ru.sr.nineteen.composeview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.sr.nineteen.theme.GameTheme

@Composable
fun BaseProgressIndicator(modifier: Modifier= Modifier,isVisible:Boolean,color:Color = GameTheme.colors.blue_500) {
    AnimatedVisibility(
    modifier = modifier,
    visible = isVisible
    ) {
        CircularProgressIndicator(color = GameTheme.colors.blue_500)
    }
}