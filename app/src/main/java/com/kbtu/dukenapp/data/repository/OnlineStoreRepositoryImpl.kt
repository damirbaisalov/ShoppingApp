package com.kbtu.dukenapp.data.repository

import com.kbtu.dukenapp.data.api.OnlineStoreService
import com.kbtu.dukenapp.data.local.CartDao
import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.products.CartItemDBModel
import com.kbtu.dukenapp.data.model.products.CategoryApiModel
import com.kbtu.dukenapp.data.model.products.ProductItemApiModel
import com.kbtu.dukenapp.data.model.products.toProductUiModel
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import com.kbtu.dukenapp.presentation.model.ProductUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class OnlineStoreRepositoryImpl(
    private val service: OnlineStoreService,
    private val cartDao: CartDao
) : OnlineStoreRepository {

    override val cartItemsFlow: Flow<List<ProductUiModel>> = cartDao.observeCartItems()
        .map { it.map { cartItem -> cartItem.toProductUiModel() } }
        .flowOn(Dispatchers.Default)

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

    override suspend fun addProductToCart(cartItem: CartItemDBModel) {
        try {
            withContext(Dispatchers.IO) {
                val existingProduct = cartDao.getCartItemByProductId(cartItem.productId)
                if (existingProduct != null) {
                    cartDao.updateCartItem(existingProduct.copy(count = existingProduct.count + 1))
                } else {
                    cartDao.addProductToCart(cartItem.copy(count = 1))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun removeProductFromCart(cartItem: CartItemDBModel) {
        try {
            withContext(Dispatchers.IO) {
                val existingProduct = cartDao.getCartItemByProductId(cartItem.productId)
                if (existingProduct != null) {
                    if (existingProduct.count > 1) {
                        cartDao.updateCartItem(existingProduct.copy(count = existingProduct.count - 1))
                    } else {
                        cartDao.deleteCartItem(existingProduct)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteProductFromCart(cartItem: CartItemDBModel) {
        try {
            withContext(Dispatchers.IO) {
                val existingProduct = cartDao.getCartItemByProductId(cartItem.productId)
                existingProduct?.let {
                    cartDao.deleteCartItem(existingProduct)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getCartItems(): List<CartItemDBModel> {
        return try {
            withContext(Dispatchers.IO) {
                cartDao.getAllCartItems()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
