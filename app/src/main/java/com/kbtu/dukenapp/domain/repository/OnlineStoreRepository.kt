package com.kbtu.dukenapp.domain.repository

import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.products.CategoryApiModel
import com.kbtu.dukenapp.data.model.products.ProductItemApiModel

interface OnlineStoreRepository {

    suspend fun loadProducts(): ResponseResult<List<ProductItemApiModel>>

    suspend fun loadCategories(): List<CategoryApiModel>

    suspend fun loadProduct(id: Int): ProductItemApiModel
}