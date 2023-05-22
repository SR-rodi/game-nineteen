package ru.sr.nineteen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

        installSplashScreen()

        setContent {
                GameTheme(isNightMode = false) {
                    this@MainActivity.window.statusBarColor = GameTheme.colors.background.toArgb()

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