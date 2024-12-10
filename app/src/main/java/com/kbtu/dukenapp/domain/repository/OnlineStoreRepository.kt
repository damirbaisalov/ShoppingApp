package com.kbtu.dukenapp.domain.repository

import com.kbtu.dukenapp.domain.model.characters.ProductItem
import com.kbtu.dukenapp.domain.network.Response

interface OnlineStoreRepository {
    suspend fun getCharacters(): Response<List<ProductItem>>
}