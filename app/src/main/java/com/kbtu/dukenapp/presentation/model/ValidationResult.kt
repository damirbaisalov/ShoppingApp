package com.kbtu.dukenapp.presentation.model

import com.kbtu.dukenapp.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null,
)