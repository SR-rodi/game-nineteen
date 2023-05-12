package ru.sr.nineteen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sr.nineteen.navgraph.navcomponent.setNavigate
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.theme.LocalRootController


class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameTheme(isNightMode = false) {

                this.window.statusBarColor = GameTheme.colors.background.toArgb()

                NavHost(
                    modifier = Modifier
                        .background(GameTheme.colors.background)
                        .fillMaxSize(),
                    navController = LocalRootController.current,
                    startDestination = viewModel.getStartScreen(),
                ) {
                    setNavigate()
                }
            }

            /*                val configuration = OdysseyConfiguration(
                                startScreen = StartScreen.Custom(*//*NavigationTree.Profile.name*//*viewModel.getStartScreen()),
                    canvas = this,
                    displayType = DisplayType.FullScreen,
                    backgroundColor = GameTheme.colors.background,
                )

                setNavigationContent(
                    onApplicationFinish = { finish() },
                    configuration = configuration
                ) {
                    getNavGraph()
                }*/
        }
    }
}