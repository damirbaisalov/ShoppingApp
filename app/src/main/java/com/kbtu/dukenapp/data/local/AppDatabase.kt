package com.kbtu.dukenapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kbtu.dukenapp.data.model.Converters
import com.kbtu.dukenapp.data.model.orders.OrderDBModel
import com.kbtu.dukenapp.data.model.products.CartItemDBModel
import com.kbtu.dukenapp.data.model.user.UserDBModel

@Database(entities = [UserDBModel::class, CartItemDBModel::class, OrderDBModel::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao
}