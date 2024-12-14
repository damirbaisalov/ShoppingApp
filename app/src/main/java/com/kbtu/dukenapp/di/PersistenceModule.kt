package com.kbtu.dukenapp.di

import android.content.Context
import androidx.room.Room
import com.kbtu.dukenapp.data.local.AppDatabase
import com.kbtu.dukenapp.data.local.AuthorizationDao
import com.kbtu.dukenapp.data.local.CartDao
import com.kbtu.dukenapp.utils.Constants.LOCATION_DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


fun provideAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        LOCATION_DB
    ).build()
}

fun provideLocationDao(database: AppDatabase): AuthorizationDao {
    return database.onlineStoreDao()
}

fun provideCartDao(database: AppDatabase): CartDao {
    return database.cartDao()
}

val persistenceModule = module {
    single { provideAppDatabase(androidContext()) }
    single { provideLocationDao(get()) }
    single { provideCartDao(get()) }
}


