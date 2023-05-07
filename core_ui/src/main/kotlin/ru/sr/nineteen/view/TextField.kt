package ru.sr.nineteen.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.sr.nineteen.core_ui.R
import ru.sr.nineteen.theme.GameTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    hintId: Int = R.string.core_ui_Password,
    isEnable: Boolean = true,
    isError: Boolean = false,
    isRepeatMode: Boolean = false,
    onValueChange: (String) -> Unit,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = GameTheme.colors.blue_500,
        unfocusedBorderColor = GameTheme.colors.blue_500,
        focusedLabelColor = GameTheme.colors.blue_500,
        cursorColor = GameTheme.colors.blue_500,
        selectionColors = TextSelectionColors(
            handleColor = GameTheme.colors.blue_500, backgroundColor = GameTheme.colors.blue_100
        )

    ),
) {

    var isVisibilityPassword by remember { mutableStateOf(false) }

    OutlinedTextField(modifier = modifier,
        value = value,
        enabled = isEnable,
        isError = isError,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            //imeAction = if (!isRepeatMode) ImeAction.Done else ImeAction.Next
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Lock,
                contentDescription = null,
                tint = GameTheme.colors.blue_500
            )
        },
        visualTransformation = if (isVisibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { isVisibilityPassword = !isVisibilityPassword }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.core_ui_eye),
                    contentDescription = null,
                    tint = if (isVisibilityPassword) GameTheme.colors.blue_400 else GameTheme.colors.blue_500
                )
            }
        },
        label = { Text(text = stringResource(id = hintId)) },
        colors = colors,
        supportingText = {
            if (!isRepeatMode)
                Column {
                    AnimatedVisibility(visible = isError) {
                        Text(text = stringResource(id = R.string.core_ui_password_helper_error))
                    }
                    Text(text = stringResource(id = R.string.core_ui_password_helper))
                }

        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    hintId: Int = R.string.core_ui_Email,
    onValueChange: (String) -> Unit,
    isEnable: Boolean = true,
    isError: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = GameTheme.colors.blue_500,
        unfocusedBorderColor = GameTheme.colors.blue_500,
        focusedLabelColor = GameTheme.colors.blue_500,
        cursorColor = GameTheme.colors.blue_500,
        selectionColors = TextSelectionColors(
            handleColor = GameTheme.colors.blue_500, backgroundColor = GameTheme.colors.blue_100
        )
    ),
    onClickTrailingIcon: () -> Unit,
) {

    OutlinedTextField(modifier = modifier,
        value = value,
        enabled = isEnable,
        isError = isError,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email, imeAction = imeAction
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Email,
                contentDescription = null,
                tint = GameTheme.colors.blue_500
            )
        },
        trailingIcon = {
            IconButton(onClick = onClickTrailingIcon) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    tint = GameTheme.colors.blue_500
                )
            }
        },
        label = { Text(text = stringResource(id = hintId)) },
        colors = colors,
        supportingText = {
            AnimatedVisibility(visible = isError) {
                Text(
                    text = stringResource(id = R.string.core_ui_email_helper_error)
                )
            }
        })

}