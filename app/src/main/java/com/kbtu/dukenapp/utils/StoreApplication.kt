package com.kbtu.dukenapp.utils

import android.app.Application
import com.kbtu.dukenapp.di.mapperModule
import com.kbtu.dukenapp.di.navigationModule
import com.kbtu.dukenapp.di.networkModule
import com.kbtu.dukenapp.di.persistenceModule
import com.kbtu.dukenapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StoreApplication)
            modules(
                navigationModule,
                repositoryModule,
                networkModule,
                mapperModule,
                persistenceModule,
            )
        }
    }
}
