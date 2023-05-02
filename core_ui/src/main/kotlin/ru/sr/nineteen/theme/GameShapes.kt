package ru.sr.nineteen.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class GameShapes(
    val small: Shape,
    val medium: Shape,
    val large: Shape,
)

val shapes = GameShapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(20.dp)
)

val LocalShapeProvider =
    staticCompositionLocalOf<GameShapes> { error("No default implementation") }