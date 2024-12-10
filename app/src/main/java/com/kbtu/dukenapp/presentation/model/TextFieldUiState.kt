package com.kbtu.dukenapp.presentation.model

import androidx.compose.runtime.MutableState
import com.kbtu.dukenapp.domain.model.TextInput

data class TextFieldUiState(
    var input: MutableState<String>,
    val textInput: TextInput = TextInput.BASE,
    var validationResult: MutableState<ValidationResult>,
    val onFieldChanged: () -> Unit? = {}
)
