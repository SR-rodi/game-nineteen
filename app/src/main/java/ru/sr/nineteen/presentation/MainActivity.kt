package ru.sr.nineteen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.navgraph.navcomponent.setNavigate
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.theme.LocalRootController


class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }

        setContent {
            GameTheme(isNightMode = false) {

                this@MainActivity.window.statusBarColor = GameTheme.colors.blue_400.toArgb()
                this@MainActivity.window.navigationBarColor = GameTheme.colors.background.toArgb()
                this@MainActivity.window.navigationBarDividerColor = GameTheme.colors.blue_400.toArgb()

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

        }
    }
}