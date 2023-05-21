package ru.sr.nineteen.navgraph.navcomponent

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import ru.sr.nineteen.domain.NavigationTree


internal fun NavOptionsBuilder.setPopUp(destination: String?, launchFlag: LaunchFlag) {
    if (destination != null)
        when (launchFlag) {
            LaunchFlag.SimpleNavigation -> {}
            is LaunchFlag.ClearDestination -> {

                popUpTo(launchFlag.destination) {
                    inclusive = true
                }
            }

            LaunchFlag.ClearPrevious -> {
                popUpTo(destination) {
                    this.inclusive = true
                }
            }

            LaunchFlag.ClearNavGraph ->  popUpTo(0)

        }
}


fun <I> NavController.push(
    route: NavigationTree,
    params: I,
    launchFlag: LaunchFlag = LaunchFlag.SimpleNavigation,
) {

    Log.e("Kart","key = ${route.key}")
    currentBackStackEntry?.savedStateHandle?.set(
        key = route.key,
        value = params,
    )
    navigate(route.name) {
        setPopUp(currentDestination?.route, launchFlag)
    }
}

fun NavController.push(
    route: NavigationTree,
    launchFlag: LaunchFlag = LaunchFlag.SimpleNavigation,
) {
    navigate(route.name) {

        setPopUp(currentDestination?.route, launchFlag)
    }
}


sealed interface LaunchFlag {

    class ClearDestination(val destination: String) : LaunchFlag
    object SimpleNavigation : LaunchFlag
    object ClearPrevious : LaunchFlag
    object ClearNavGraph : LaunchFlag
}