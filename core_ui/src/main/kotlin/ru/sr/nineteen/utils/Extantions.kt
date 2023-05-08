package ru.sr.nineteen.utils

import ru.alexgladkov.odyssey.compose.Render
import ru.alexgladkov.odyssey.compose.controllers.ModalController
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.AlertConfiguration
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration

fun ModalController.presentAlertDialog(
    maxHeight: Float? = null,
    maxWith: Float? = 0.9f,
    cornerRadius: Int = 10,
    closeOnBackdropClick: Boolean = true,
    content: Render,
) {
    present(
        alertConfiguration = AlertConfiguration(
            maxHeight = maxHeight, maxWidth = maxWith, cornerRadius = cornerRadius,
            closeOnBackdropClick = closeOnBackdropClick
        ),
        content = content
    )
}