package ru.sr.nineteen.navgraph

import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.sr.nineteen.presentation.field.compose.GameScreen
import ru.sr.nineteen.compose.RatingScreen
import ru.sr.nineteen.TrainingScreen
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.presentation.compose.MenuScreen
import ru.sr.nineteen.presentation.registration.compose.RegistrationScreen
import ru.sr.nineteen.presentation.resetpassword.compose.ResetPasswordScreen
import ru.sr.nineteen.presentation.signin.compose.screen.SignInScreen
import ru.sr.nineteen.presentation.win.compose.WinScreen

fun RootComposeBuilder.getNavGraph() {
    screen(NavigationTree.SignIn.name) { SignInScreen() }
    screen(NavigationTree.Registration.name) { email -> RegistrationScreen(email as String) }
    screen(NavigationTree.ResetPassword.name) { email -> ResetPasswordScreen(email as String) }
    screen(NavigationTree.Menu.name) { MenuScreen() }
    screen(NavigationTree.Training.name) { TrainingScreen() }
    screen(NavigationTree.Game.name) { settings -> GameScreen(settings as SettingGame) }
    screen(NavigationTree.Rating.name) { RatingScreen() }
    screen(NavigationTree.Win.name) { settings -> WinScreen(settings as SettingGame) }
}