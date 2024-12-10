package com.kbtu.dukenapp.data.repository

import com.kbtu.dukenapp.data.api.OnlineStoreService
import com.kbtu.dukenapp.data.mapper.ProductsMapper
import com.kbtu.dukenapp.domain.model.characters.ProductItem
import com.kbtu.dukenapp.domain.network.Response
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.utils.extension.catchError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnlineStoreRepositoryImpl(
    private val service: OnlineStoreService,
    private val mapper: ProductsMapper
) : OnlineStoreRepository {

    override suspend fun getCharacters(): Response<List<ProductItem>> = mapper.map(
        try {
            withContext(Dispatchers.IO) {
                val data = service.getProductList()
                Response.Success(data)
            }
        } catch (e: Exception) {
            e.catchError()
        }
    )
}
