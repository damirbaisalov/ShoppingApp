package com.kbtu.dukenapp.data.local

import androidx.room.*
import com.kbtu.dukenapp.data.model.orders.OrderDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders")
    fun getOrdersFlow(): Flow<List<OrderDBModel>>


//    @Query("SELECT * FROM orders WHERE userId = :userId")
//    suspend fun getOrdersByUserIdFlow(userId: Int): Flow<List<OrderDBModel>>

    // Insert an order
    @Insert
    suspend fun insertOrder(order: OrderDBModel): Long

    // Get orders by user ID
    @Query("SELECT * FROM orders WHERE userId = :userId")
    suspend fun getOrdersByUserId(userId: Int): List<OrderDBModel>

    // Get a specific order by orderId
    @Query("SELECT * FROM orders WHERE orderId = :orderId LIMIT 1")
    suspend fun getOrderById(orderId: Int): OrderDBModel?

    // Get all orders
    @Query("SELECT * FROM orders")
    suspend fun getAllOrders(): List<OrderDBModel>
}
