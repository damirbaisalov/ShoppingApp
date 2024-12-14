package com.kbtu.dukenapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kbtu.dukenapp.data.model.products.CartItemDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_items")
    fun observeCartItems(): Flow<List<CartItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(cartItem: CartItemDBModel)

    @Query("DELETE FROM cart_items WHERE product_id = :productId")
    suspend fun removeProductFromCart(productId: Int)

    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItemDBModel>

    @Query("SELECT * FROM cart_items WHERE product_id = :productId")
    suspend fun getCartItemByProductId(productId: Int): CartItemDBModel?

    @Query("UPDATE cart_items SET count = :quantity WHERE product_id = :productId")
    suspend fun updateCartItemQuantity(productId: Int, quantity: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemDBModel)

    @Update
    suspend fun updateCartItem(cartItem: CartItemDBModel)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItemDBModel)

}
