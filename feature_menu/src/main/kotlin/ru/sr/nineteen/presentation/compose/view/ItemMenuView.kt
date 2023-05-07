package ru.sr.nineteen.presentation.compose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.presentation.viewmodel.model.MenuState
import ru.sr.nineteen.theme.GameTheme
import ru.sr.nineteen.view.ActionButtonView

@Composable
fun ItemMenuView(
    modifier: Modifier = Modifier,
    isEnable:Boolean = true,
    title: String,
    textButton: String,
    imageID: Int,
    onClickButton: () -> Unit,
) {
    Box(
        modifier = Modifier.then(modifier).padding(top = 6.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.heightIn(10.dp))
                Text(
                    text = title,
                    style = GameTheme.fonts.h3.copy(color = GameTheme.colors.textTitle),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.heightIn(10.dp))

                ActionButtonView(padding = PaddingValues(horizontal = 16.dp),
                    text = textButton,
                    enabled = isEnable) { onClickButton() }
            }
        }
        Image(
            modifier = Modifier.size(125.dp),
            painter = painterResource(id = imageID),
            contentDescription = title
        )
    }
}