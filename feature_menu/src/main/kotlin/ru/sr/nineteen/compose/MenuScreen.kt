package ru.sr.nineteen.compose

import android.view.Menu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.sr.nineteen.theme.GameTheme

@Composable
fun MenuScreen() {

    Text(text = "Menu", style =GameTheme.fonts.h1)

}