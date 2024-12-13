package com.kbtu.dukenapp.presentation.features.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.kbtu.dukenapp.presentation.features.home.mvi.Intent
import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.ui.theme.black

@Composable
fun CategoryList(categories: List<CategoryUiModel>, performIntent: (Intent) -> Unit) {

    LazyRow(
        modifier = Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {
        items(categories) { category ->
            CategoryChip(category) {
                performIntent.invoke(Intent.OnCategoryClick(category.id))
            }
        }
    }
}

@Composable
fun CategoryChip(category: CategoryUiModel, onCategoryClick: () -> Unit) {
    OutlinedButton(
        onClick = { onCategoryClick() },
        modifier = Modifier
            .padding(end = 8.dp)
            .height(48.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = black
        ),
        border = BorderStroke(1.dp, LightGray)
    ) {
        Text(text = category.name, style = MaterialTheme.typography.bodyMedium)
    }
}