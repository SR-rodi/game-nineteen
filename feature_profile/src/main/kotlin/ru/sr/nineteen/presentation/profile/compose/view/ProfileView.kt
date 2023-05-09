package ru.sr.nineteen.presentation.profile.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.core_ui.R
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileEvent
import ru.sr.nineteen.presentation.profile.viewmodel.ProfileState
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView
import ru.sr.nineteen.view.AvatarTypeClick

@Composable
fun ProfileView(state: ProfileState, eventHandler: (ProfileEvent) -> Unit) {

    Column {
        ProfileAvatarView(
            avatar = state.user.avatar ?: state.avatarUri ?: R.drawable.kitekat_4,
            isVisibilitySaveButton = state.isSaveButtonVisibility,
            isUpLoadImage = state.isUpLoadAvatar
        ) { clickType ->

            eventHandler(
                if (clickType == AvatarTypeClick.Image) ProfileEvent.OnClickAvatar
                else ProfileEvent.OnClickSaveAvatar
            )
        }
        UserInfoCard(title = stringResource(id = R.string.core_ui_Email), value = state.user.email)
        UserInfoCard(
            title = stringResource(id = R.string.core_ui_name),
            value = state.user.name ?: "N/A",
            isEdit = true
        ) {
            eventHandler(ProfileEvent.OnClickUserName)
        }
        ButtonGroup(eventHandler)
    }
}

@Composable
fun UserInfoCard(title: String, value: String, isEdit: Boolean = false, onClick: () -> Unit = {}) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (isEdit) {
                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = onClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit, contentDescription = "edit text"
                    )
                }

            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = GameTheme.fonts.p.copy(color = GameTheme.colors.textTitle)
                )
                Text(text = value, style = GameTheme.fonts.h3.copy(color = GameTheme.colors.text))
            }
        }
    }


}

@Composable
fun ButtonGroup(eventHandler: (ProfileEvent) -> Unit) {

    Row(Modifier.padding(horizontal = 16.dp)) {
        Box(modifier = Modifier.weight(1f)) {
            ActionButtonView(text = "Выйти") {
                eventHandler(ProfileEvent.OnClickLogOutButton)
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            ActionButtonView(text = "Удалить") {
                eventHandler(ProfileEvent.OnClickDeleteOutButton)
            }
        }

    }

}