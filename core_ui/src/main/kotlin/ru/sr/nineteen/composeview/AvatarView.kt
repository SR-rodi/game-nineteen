package ru.sr.nineteen.composeview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import ru.sr.nineteen.core_ui.R
import ru.sr.nineteen.theme.GameTheme

@Composable
fun AvatarView(
    image: Any?,
    padding: PaddingValues = PaddingValues(top = 50.dp),
    widthBorder: Dp = 2.dp,
    iconColor: Color = GameTheme.colors.blue_500,
    size: Dp = 100.dp,
    borderColor: Color = GameTheme.colors.blue_100,
    shapes: Shape = GameTheme.shapes.large,
    isVisibilitySaveButton: Boolean = false,
    isUploadAvatar: Boolean = false,
    isEdit: Boolean = true,
    onClick: (AvatarTypeClick) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .padding(padding)
            .clip(shapes)

            .background(borderColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(shapes)
                .size(size)
                .clickable { onClick(AvatarTypeClick.Image) }
                .border(width = widthBorder, color = borderColor, shapes),
        ) {
            SubcomposeAsyncImage(
                model = image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                            .scale(0.3f),
                        color = GameTheme.colors.blue_500
                    )

                }
            )
            if (isEdit)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(shapes)
                        .alpha(0.8f)
                        .background(borderColor)
                ) {
                    Icon(
                        modifier = Modifier.padding(4.dp),
                        imageVector = Icons.Filled.Edit, contentDescription = "",
                        tint = iconColor
                    )
                }

        }
        AnimatedVisibility(
            visible = isVisibilitySaveButton
        ) {

            if (isUploadAvatar)
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(size / 2)
                        .scale(0.5f),
                    color = iconColor
                )
            else
                IconButton(
                    modifier = Modifier
                        .size(size / 2)
                        .background(borderColor),
                    onClick = { onClick(AvatarTypeClick.SaveButton) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.core_ui_ic_save),
                        contentDescription = "Save Button",
                        tint = iconColor
                    )


                }
        }
    }
}

enum class AvatarTypeClick {
    Image, SaveButton
}

@Preview(showBackground = true)
@Composable
fun AvatarPreview() {
    GameTheme {
        AvatarView(
            isVisibilitySaveButton = true,
            image = "https://firebasestorage.googleapis.com/v0/b/emaillinkregistration.appspot.com/o/Avatars%2Favatar_prZbDtjEwSMIwnq6BB8OMBz68xP2.jpg?alt=media&token=f6b55b7c-ef0f-4a0f-8edc-9c3b5069f175"
        )
    }
}