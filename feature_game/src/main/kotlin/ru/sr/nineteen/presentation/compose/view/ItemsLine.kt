package ru.sr.nineteen.presentation.compose.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sr.nineteen.domain.gameitem.GameItemEngine
import ru.sr.nineteen.domain.gameitem.StatusItem

@Composable
fun ItemsLine(items: List<GameItemEngine>, onClickItem: (position:Int) -> Unit) {
        Row(modifier = Modifier.fillMaxWidth()) {
            items.forEachIndexed { index,gameItem ->
            Box(modifier = Modifier.weight(1f)) {
                when (gameItem.statusItem) {

                    StatusItem.CHOICE -> GameItemChoiceView(item = gameItem)

                    StatusItem.NOT_CHOICE ->
                        GameItemNotChoiceView(item = gameItem) { onClickItem(index) }

                    StatusItem.SELECT ->
                        GameItemSelectView(item = gameItem) { onClickItem(index) }

                    StatusItem.HELP -> GameItemHelpView(item = gameItem) { onClickItem(index) }
                }
            }
        }
    }
}