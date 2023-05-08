package ru.sr.nineteen.presentation.compose.screen.view

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
import ru.sr.nineteen.core_ui.R
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.AvatarView


@Composable
fun ProfileAvatarView(
    widthBorder: Dp = 2.dp,
    background: Color = GameTheme.colors.blue_400,
    avatarHeight: Dp = 100.dp,
    borderColor: Color = GameTheme.colors.blue_100,
    onClickImage: () -> Unit = {},
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
            image = R.drawable.kitekat_2,
            padding = PaddingValues(top = 50.dp),
            widthBorder = widthBorder,
            borderColor = borderColor,
            size = avatarHeight
        ) { onClickImage() }
    }

}
