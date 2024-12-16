package com.kbtu.dukenapp.data.repository

import com.kbtu.dukenapp.data.api.OnlineStoreService
import com.kbtu.dukenapp.data.local.CartDao
import com.kbtu.dukenapp.data.local.OrderDao
import com.kbtu.dukenapp.data.local.UserDao
import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.orders.OrderDBModel
import com.kbtu.dukenapp.data.model.orders.OrderStatus
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class OnlineStoreRepositoryImpl(
    private val service: OnlineStoreService,
    private val cartDao: CartDao,
    private val orderDao: OrderDao,
    private val userDao: UserDao
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

    override suspend fun createOrder(
        userId: Int,
        totalPrice: Double,
        successResult: () -> Unit,
        errorResult: (String) -> Unit
    ) {
        try {
            withContext(Dispatchers.IO) {
                val user = userDao.getUserById(userId)
                if (user == null) {
                    errorResult("User with id $userId does not exist")
                    return@withContext
                }

                val productIds = cartDao.getAllCartItems().map { it.productId }

                val statuses = OrderStatus.entries.toTypedArray()
                val randomStatus = statuses[Random.nextInt(statuses.size)]

                val orderDbModel = OrderDBModel(
                    userId = user.id,
                    status = randomStatus,
                    totalPrice = totalPrice,
                    createdDate = getCurrentDate(),
                    productIds = productIds.joinToString(separator = ",")
                )

                orderDao.insertOrder(orderDbModel)
                cartDao.clearAllCartItems()
                successResult()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorResult(e.message ?: "Something went wrong")
        }
    }


    private fun getCurrentDate(): String {
        val date = Date()
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return formatter.format(date)
    }
}
