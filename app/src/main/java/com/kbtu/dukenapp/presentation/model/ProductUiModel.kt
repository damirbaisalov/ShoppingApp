package com.kbtu.dukenapp.presentation.model

data class ProductUiModel(
    val productId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val images: List<String>,
    val category: CategoryUiModel
)

data class CategoryUiModel(
    val id: Int,
    val name: String,
    val image: String
)