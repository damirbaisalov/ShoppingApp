package com.kbtu.dukenapp.data.mapper

import com.kbtu.dukenapp.data.model.products.ProductsApiModel
import com.kbtu.dukenapp.domain.mapper.Mapper
import com.kbtu.dukenapp.domain.model.characters.ProductItem

class ProductsMapper : Mapper<ProductsApiModel, List<ProductItem>>() {

    // Map ProductsApiModel to a List of ProductItem
    override fun mapData(from: ProductsApiModel): List<ProductItem> {
        return from.products?.map {
            ProductItem(
                productId = it.productId ?: 0,
                name = it.name ?: "",
                description = it.description ?: "",
                price = it.price ?: 0.0,
                imageUrl = it.imageUrl ?: "",
                category = it.category ?: ""
            )
        }.orEmpty()
    }
}