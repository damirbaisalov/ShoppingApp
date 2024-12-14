package com.kbtu.dukenapp.data.api

import com.kbtu.dukenapp.data.model.products.CategoryApiModel
import com.kbtu.dukenapp.data.model.products.ProductItemApiModel
import com.kbtu.dukenapp.data.model.products.ProductsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface OnlineStoreService {

    @GET("products?offset=0&limit=15")
    suspend fun getProductList(): List<ProductItemApiModel>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductItemApiModel

    @GET("categories")
    suspend fun getCategories(): List<CategoryApiModel>

    @GET("products/?categoryId={id}")
    suspend fun filterByCategory(@Path("id") id: Int): List<ProductItemApiModel>
}