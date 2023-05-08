package ru.sr.nineteen.presentation.compose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.core.LaunchFlag
import ru.sr.nineteen.domain.NavigationTree
import ru.sr.nineteen.presentation.viewmodel.ProfileDeleteAction
import ru.sr.nineteen.presentation.viewmodel.ProfileDeleteEvent
import ru.sr.nineteen.presentation.viewmodel.ProfileDeleteState
import ru.sr.nineteen.presentation.viewmodel.ProfileDeleteWarningViewModel
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.Screen

@Composable
fun ProfileDeleteWarningScreen(viewModel: ProfileDeleteWarningViewModel = koinViewModel()) {

    Screen(viewModel = viewModel) { state, action, rootController ->
        ProfileDeleteWarningView(state) { event -> viewModel.obtainEvent(event) }

        when (action) {
            ProfileDeleteAction.OpenSignInScreen -> {
                rootController.push(
                    NavigationTree.SignIn.name,
                    launchFlag = LaunchFlag.SingleNewTask
                )
                viewModel.obtainEvent(ProfileDeleteEvent.OnResetAction)
            }

            ProfileDeleteAction.PopToBackStack -> {
                rootController.popBackStack()
                viewModel.obtainEvent(ProfileDeleteEvent.OnResetAction)
            }

            null -> {}
        }
    }

}


@Composable
fun ProfileDeleteWarningView(
    state: ProfileDeleteState,
    eventHandler: (ProfileDeleteEvent) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Вы уверены что хотите удалить свой Аккаунт?",
            style = GameTheme.fonts.h2.copy(color = GameTheme.colors.textTitle),
            textAlign = TextAlign.Center
        )
        Row(Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                ActionButtonView(text = "Да", isOutLine = true) {
                    eventHandler(ProfileDeleteEvent.OnClickYesButton)
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                ActionButtonView(text = "Нет") {
                    eventHandler(ProfileDeleteEvent.OnClickNoButton)
                }
            }

        }

    }
}
