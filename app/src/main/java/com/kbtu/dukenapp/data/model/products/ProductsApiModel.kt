package com.kbtu.dukenapp.data.model.products

data class ProductsApiModel(
    val products: List<ProductItemApiModel>?, // Matches "products" in the JSON response
)