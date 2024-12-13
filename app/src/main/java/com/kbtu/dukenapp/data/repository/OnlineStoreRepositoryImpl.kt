package com.kbtu.dukenapp.data.repository

import com.kbtu.dukenapp.data.api.OnlineStoreService
import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.products.CategoryApiModel
import com.kbtu.dukenapp.data.model.products.ProductItemApiModel
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnlineStoreRepositoryImpl(
    private val service: OnlineStoreService
) : OnlineStoreRepository {

    override suspend fun loadProducts(): ResponseResult<List<ProductItemApiModel>> {
        return try {
            withContext(Dispatchers.IO) {
                val data = service.getProductList()
                ResponseResult.Success(data)
            }
        } catch (e: Exception) {
            ResponseResult.Error(e)
        }
    }

    override suspend fun loadCategories(): List<CategoryApiModel> {
        return try {
            withContext(Dispatchers.IO) {
                val data = service.getCategories()
                data
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun loadProduct(id: Int): ProductItemApiModel {
        return withContext(Dispatchers.IO) {
            service.getProduct(id)
        }
    }
}
