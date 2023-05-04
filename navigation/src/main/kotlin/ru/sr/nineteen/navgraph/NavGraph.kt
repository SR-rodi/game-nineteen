package ru.sr.nineteen.navgraph

import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.sr.nineteen.presentation.compose.GameScreen
import ru.sr.nineteen.RatingScreen
import ru.sr.nineteen.TrainingScreen
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.domain.gameitem.SettingGame
import ru.sr.nineteen.presentation.compose.MenuScreen

fun RootComposeBuilder.getNavGraph(){
    screen(NavigationTree.Menu.name){ MenuScreen() }
    screen(NavigationTree.Training.name){TrainingScreen()}
    screen(NavigationTree.Game.name){ settings-> GameScreen(settings as SettingGame) }
    screen(NavigationTree.Rating.name){RatingScreen()}
}