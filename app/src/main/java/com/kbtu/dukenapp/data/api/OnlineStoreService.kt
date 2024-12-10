package com.kbtu.dukenapp.data.api

import com.kbtu.dukenapp.data.model.products.ProductsApiModel
import retrofit2.http.GET

interface OnlineStoreService {

    @GET("fbf3d361-b49f-49f9-aa25-0aa79fd1cd90")
    suspend fun getProductList(): ProductsApiModel
}