package ru.sr.nineteen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import ru.alexgladkov.odyssey.core.configuration.DisplayType
import ru.sr.nineteen.R
import ru.sr.nineteen.navgraph.getNavGraph
import ru.sr.nineteen.theme.GameTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameTheme(isNightMode = false) {

                    val configuration = OdysseyConfiguration(
                        canvas = this,
                        displayType = DisplayType.FullScreen,
                        backgroundColor = GameTheme.colors.background
                    )

                    setNavigationContent(configuration) {
                            getNavGraph()
                    }
                }
            }
    }
}