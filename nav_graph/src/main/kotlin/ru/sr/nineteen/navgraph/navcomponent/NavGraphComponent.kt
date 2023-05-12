package ru.sr.nineteen.navgraph.navcomponent

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.sr.nineteen.compose.RatingScreen
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.presentation.compose.MenuScreen
import ru.sr.nineteen.presentation.field.compose.GameScreen
import ru.sr.nineteen.presentation.profile.compose.screen.ProfileScreen
import ru.sr.nineteen.presentation.registration.compose.RegistrationScreen
import ru.sr.nineteen.presentation.resetpassword.compose.screen.ResetPasswordScreen
import ru.sr.nineteen.presentation.signin.compose.screen.SignInScreen
import ru.sr.nineteen.screen.TrainingScreen
import ru.sr.nineteen.theme.LocalRootController


fun NavGraphBuilder.setNavigate() {

    composable(NavigationTree.SignIn) { SignInScreen() }
    composable<String>(NavigationTree.Registration) { email-> RegistrationScreen(email) }
    composable<String>(NavigationTree.ResetPassword) { email -> ResetPasswordScreen(email) }

    composable(NavigationTree.Menu) { MenuScreen() }
    composable<SettingGame>(NavigationTree.Game) { setting -> GameScreen(settingGame = setting) }
    composable(NavigationTree.Profile) { ProfileScreen() }
    composable(NavigationTree.Training.name) { TrainingScreen() }
    composable(NavigationTree.Rating) { RatingScreen() }
}


fun <T> NavGraphBuilder.composable(
    route: NavigationTree,
    content: @Composable (value: T) -> Unit,
) {
    composable(route.name) {
        val value =
            LocalRootController.current.previousBackStackEntry?.savedStateHandle?.get<T>(route.key)
        if (value == null) Log.e("Kart", "не задан аргумент") else content(value)

    }
}

fun NavGraphBuilder.composable(route: NavigationTree, content: @Composable () -> Unit) {
    composable(route.name) {
        content()
    }
}