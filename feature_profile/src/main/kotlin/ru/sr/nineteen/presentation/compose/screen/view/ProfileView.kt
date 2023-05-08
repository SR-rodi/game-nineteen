package ru.sr.nineteen.presentation.compose.screen.view

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.presentation.viewmodel.ProfileEvent
import ru.sr.nineteen.presentation.viewmodel.ProfileState
import ru.sr.nineteen.profile.R
import ru.sr.nineteen.theme.GameColor
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.AvatarView

@Composable
fun ProfileView(state: ProfileState, eventHandler: (ProfileEvent) -> Unit) {

    Column() {
        ProfileAvatarView()
        UserInfoCard(title = "Text", value = "Text")
        UserInfoCard(title = "Text", value = "Text")
        UserInfoCard(title = "Text", value = "Text")
        ButtonGroup(eventHandler)
    }
}

@Composable
fun UserInfoCard(title: String, value: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = title, style = GameTheme.fonts.p.copy(color = GameTheme.colors.textTitle))
            Text(text = value, style = GameTheme.fonts.h3.copy(color = GameTheme.colors.text))
        }
    }

}

@Composable
fun ButtonGroup(eventHandler: (ProfileEvent) -> Unit){

    Row(Modifier.padding(horizontal = 16.dp)) {
        Box(modifier = Modifier.weight(1f)){
            ActionButtonView(text = "Выйти") {
                eventHandler(ProfileEvent.OnClickLogOutButton)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.weight(1f)){
            ActionButtonView(text = "Удалить") {
                eventHandler(ProfileEvent.OnClickDeleteOutButton)
            }
        }

    }

}