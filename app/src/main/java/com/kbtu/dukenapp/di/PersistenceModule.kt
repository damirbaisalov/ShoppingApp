package com.kbtu.dukenapp.di

import android.content.Context
import androidx.room.Room
import com.kbtu.dukenapp.data.local.AppDatabase
import com.kbtu.dukenapp.data.local.CartDao
import com.kbtu.dukenapp.data.local.OrderDao
import com.kbtu.dukenapp.data.local.UserDao
import com.kbtu.dukenapp.utils.Constants.LOCATION_DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun provideAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        LOCATION_DB
    )
        .fallbackToDestructiveMigration()
        .build()
}

fun provideCartDao(database: AppDatabase): CartDao {
    return database.cartDao()
}

fun provideUserDao(database: AppDatabase): UserDao {
    return database.userDao()
}

fun provideOrderDao(database: AppDatabase): OrderDao {
    return database.orderDao()
}

val persistenceModule = module {
    single { provideAppDatabase(androidContext()) }
    single { provideCartDao(get()) }
    single { provideUserDao(get()) }
    single { provideOrderDao(get()) }
}


