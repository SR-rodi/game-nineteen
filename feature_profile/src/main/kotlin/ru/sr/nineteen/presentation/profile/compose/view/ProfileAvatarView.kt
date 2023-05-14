package ru.sr.nineteen.presentation.profile.compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.composeview.AvatarTypeClick
import ru.sr.nineteen.composeview.AvatarView


@Composable
fun ProfileAvatarView(
    avatar:Any,
    widthBorder: Dp = 2.dp,
    background: Color = GameTheme.colors.blue_400,
    avatarHeight: Dp = 100.dp,
    borderColor: Color = GameTheme.colors.blue_100,
    isVisibilitySaveButton:Boolean,
    isUpLoadImage:Boolean,
    onClickImage: (AvatarTypeClick) -> Unit = {},
) {

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(Modifier.fillMaxWidth()) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(background)
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(widthBorder)
                    .background(borderColor)
            )
        }
        AvatarView(
            image = avatar,
            padding = PaddingValues(top = 50.dp),
            widthBorder = widthBorder,
            borderColor = borderColor,
            size = avatarHeight,
            isVisibilitySaveButton = isVisibilitySaveButton,
            isUploadAvatar = isUpLoadImage
        ) {clickType->
            onClickImage(clickType)
        }
    }

}
