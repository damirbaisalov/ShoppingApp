package com.kbtu.dukenapp.domain.use_case

import com.kbtu.dukenapp.domain.model.TextInput
import com.kbtu.dukenapp.presentation.model.ValidationResult
import com.kbtu.dukenapp.utils.UiText

class ValidateField {

    operator fun invoke(text: String, textInput: TextInput): ValidationResult {
        if (text.isBlank())
            return ValidationResult(
                successful = false,
                errorMessage = UiText.DynamicString("Field is required"),
            )


        return ValidationResult(successful = true)
    }
}