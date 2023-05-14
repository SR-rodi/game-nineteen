package ru.sr.nineteen.presentation.compose.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.presentation.model.RatingUIModel
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.AvatarView

@Composable
fun RatingItem(item: RatingUIModel,index:Int) {

    var isExpanded by remember { mutableStateOf(false) }

    val rotationArrow by animateFloatAsState(targetValue = if (isExpanded) -180f else 0f)

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(GameTheme.shapes.large)
            .background(GameTheme.colors.blue_100),) {

        Row(Modifier
            .fillMaxWidth().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = (index + 1).toString())
            AvatarView(
                image = item.userAvatar,
                padding = PaddingValues(),
                size = 50.dp,
                isEdit = false
            )
            Text(
                modifier = Modifier.weight(1f),
                text = item.userName.toString()
            )
            Text(
                modifier = Modifier.weight(1f),
                text = item.steps.toString(),
                textAlign = TextAlign.End
            )
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    modifier = Modifier.rotate(rotationArrow),
                    imageVector = Icons.Default.KeyboardArrowDown, contentDescription = ""
                )
            }
        }

        AnimatedVisibility(visible = isExpanded) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(GameTheme.colors.select)
                .height(60.dp))
        }
    }
}