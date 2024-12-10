package com.kbtu.dukenapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kbtu.dukenapp.presentation.model.TextFieldUiState
import com.kbtu.dukenapp.ui.theme.black
import com.kbtu.dukenapp.ui.theme.darkGreen
import com.kbtu.dukenapp.ui.theme.greyColor
import com.kbtu.dukenapp.ui.theme.outlineGreenColor
import com.kbtu.dukenapp.ui.theme.red
import com.kbtu.dukenapp.ui.theme.white
import com.kbtu.dukenapp.utils.extension.noRippleClickable

@Composable
fun CityTextField(
    textState: TextFieldUiState,
    placeholder: String = "City name"
) {
    CustomTextField(
        textState = textState,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrect = false
        ),
    )
}

@Composable
fun CustomEmailTextField(
    textState: TextFieldUiState,
    placeholder: String = "Email"
) {
    CustomTextField(
        textState = textState,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrect = false
        ),
    )
}

@Composable
fun CustomPasswordTextField(
    textState: TextFieldUiState,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = Icons.Filled.Info


    CustomTextField(
        textState = textState,
        placeholder = "Password:",
        trailingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        onClick = { passwordVisibility = !passwordVisibility }
                    )
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrect = false
        ),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@Composable
fun CustomTextField(
    textState: TextFieldUiState,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int = 0,
    leadingIcon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier.noRippleClickable(onClick),
        enabled = enabled,
        value = textState.input.value,
        onValueChange = {
            if (maxLength == 0 || it.length <= maxLength) {
                textState.input.value = it
                textState.onFieldChanged()
            }
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (!textState.validationResult.value.successful) {
                Text(
                    textState.validationResult.value.errorMessage?.getText() ?: "",
                    modifier = Modifier.padding(start = 0.dp)
                )
            }
        },
        isError = !textState.validationResult.value.successful,
        placeholder = {
            Text(
                placeholder
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = greyColor,
            disabledLabelColor = greyColor,
            disabledBorderColor = greyColor,
            unfocusedBorderColor = greyColor,
            focusedBorderColor = outlineGreenColor,
            focusedContainerColor = white,
            errorTextColor = red
        ),
        singleLine = true,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = black))

    BasicTextField(
        value = value,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 60.dp),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(darkGreen),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        decorationBox = @Composable { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                prefix = prefix,
                suffix = suffix,
                supportingText = supportingText,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled,
                        isError,
                        interactionSource,
                        colors,
                        shape
                    )
                }
            )
        }
    )
}