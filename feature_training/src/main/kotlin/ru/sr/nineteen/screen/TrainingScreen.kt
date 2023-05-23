package ru.sr.nineteen.screen

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import ru.sr.nineteen.composeview.Screen
import ru.sr.nineteen.screen.view.TrainingView
import ru.sr.nineteen.viewmodel.model.TrainingAction
import ru.sr.nineteen.viewmodel.TrainingViewMod


@Composable
fun TrainingScreen(viewModel: TrainingViewMod = koinViewModel()) {

    Screen(viewModel = viewModel) { state, action, navController ->
        TrainingView(state){trainingEvent -> viewModel.obtainEvent(trainingEvent) }

        when(action){
            TrainingAction.OpenBack -> navController.popBackStack()
            null -> {}
        }
    }
}