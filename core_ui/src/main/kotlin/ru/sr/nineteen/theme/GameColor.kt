package ru.sr.nineteen.theme

import android.text.style.BackgroundColorSpan
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class GameColor(
    val gray_100: Color,
    val more: Color,
    val notChoice: Color,
    val choice: Color,
    val select: Color,
    val help: Color,
    val blue_100: Color,
    val blue_200: Color,
    val blue_300: Color,
    val blue_400: Color,
    val blue_500: Color,
    val text: Color,
    val textTitle:Color,
    val textButton:Color,
    val background:Color,
)

val lightPalette = GameColor(
    gray_100 = Color(0xFF676868),
    notChoice = Color(0xFF35DAF0),
    choice = Color(0xFF839684),
    select = Color(0xFF0B5DA3),
    help = Color(0xE4D6C572),
    blue_100 = Color(0xFFEEF0F6),
    blue_200 = Color(0xFFCEDAEC),
    blue_300 = Color(0xFF9CB9D1),
    blue_400 = Color(0xFF749CD7),
    blue_500 = Color(0xFF054D74),
    text = Color(0xFF070707),
    textTitle = Color(0xFF054D74),
    textButton = Color(0xFFFFFFFF),
    more = Color(0xFF46E8FD),
    background = Color(0xFF9CB9D1)
)


val darkPalette = lightPalette.copy(gray_100 = Color(0xFF0E1B1B))

val LocalColorProvider =
    staticCompositionLocalOf<GameColor> { error("No default implementation") }