package ru.sr.nineteen.navgraph

import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.sr.nineteen.compose.MenuScreen
import ru.sr.nineteen.domain.NavigationTree

fun RootComposeBuilder.getNavGraph(){
    screen(NavigationTree.Menu.name){ MenuScreen() }
    screen(NavigationTree.Training.name){}
    screen(NavigationTree.Game.name){}
    screen(NavigationTree.Rating.name){}
}