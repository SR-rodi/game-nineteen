package ru.sr.nineteen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.sr.nineteen.domain.gameitem.SettingGame

@Composable
fun GameScreen(settingGame: SettingGame) {
    
    Text(text = settingGame.gameMode)
}