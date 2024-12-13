package com.kbtu.dukenapp.data.model.products

import com.google.gson.annotations.SerializedName
import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.presentation.model.ProductUiModel

data class ProductItemApiModel(
    @SerializedName("id") val productId: Int,
    @SerializedName("title") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    @SerializedName("images") val images: List<String>,
    @SerializedName("category") val category: CategoryApiModel
)

data class CategoryApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)

fun ProductItemApiModel.toUiModel(): ProductUiModel = ProductUiModel(
    productId = productId,
    name = name,
    description = description,
    price = price,
    images = images,
    category = category.toUiModel()
)

fun CategoryApiModel.toUiModel(): CategoryUiModel = CategoryUiModel(
    id = id,
    name = name,
    image = image
)