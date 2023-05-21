package ru.sr.nineteen.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

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
            LocalRootController provides rememberNavController(),
            content = content
        )
    }
}

val LocalRootController = compositionLocalOf<NavHostController> {
    error("No root controller provider")
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