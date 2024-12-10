package com.kbtu.dukenapp.di

import com.kbtu.dukenapp.data.api.OnlineStoreService
import com.kbtu.dukenapp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    singleOf(::provideHttpClient)
    singleOf(::provideSearchLocationService)
}

fun provideHttpClient(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return Retrofit.Builder()
        .client(
            OkHttpClient().newBuilder().addInterceptor(
                interceptor
            ).build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideSearchLocationService(retrofit: Retrofit): OnlineStoreService {
    return retrofit.create(OnlineStoreService::class.java)
}
