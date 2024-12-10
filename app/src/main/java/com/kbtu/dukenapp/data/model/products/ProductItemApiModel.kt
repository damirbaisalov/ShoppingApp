package com.kbtu.dukenapp.data.model.products

data class ProductItemApiModel(
    val productId: Int?,
    val name: String?,
    val description: String?,
    val price: Double?,
    val imageUrl: String?,
    val category: String?
)