package ru.sr.nineteen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.StartScreen
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import ru.alexgladkov.odyssey.core.configuration.DisplayType
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.navgraph.getNavGraph
import ru.sr.nineteen.theme.GameTheme


class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameTheme(isNightMode = false) {
                val configuration = OdysseyConfiguration(
                    startScreen = StartScreen.Custom(/*NavigationTree.SignIn.name*/viewModel.getStartScreen()),
                    canvas = this,
                    displayType = DisplayType.FullScreen,
                    backgroundColor = GameTheme.colors.background,
                )

                setNavigationContent(
                    onApplicationFinish = { finish() },
                    configuration = configuration
                ) {
                    getNavGraph()
                }
            }
        }
    }
}