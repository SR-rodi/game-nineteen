package ru.sr.nineteen.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun GameTheme(
    isNightMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme() {
        val colors = if (isNightMode) darkPalette else lightPalette
        CompositionLocalProvider(
            LocalColorProvider provides colors,
            LocalFontProvider provides fonts,
            LocalShapeProvider provides shapes,
            content = content
        )
    }
}

object GameTheme {
    val colors: GameColor
        @Composable
        get() = LocalColorProvider.current
    val shapes: GameShapes
        @Composable
        get() = LocalShapeProvider.current
    val fonts: GameFonts
        @Composable
        get() = LocalFontProvider.current

}