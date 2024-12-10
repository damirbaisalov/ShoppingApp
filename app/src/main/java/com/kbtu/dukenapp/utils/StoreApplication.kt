package com.kbtu.dukenapp.utils

import android.app.Application
import com.kbtu.dukenapp.di.mapperModule
import com.kbtu.dukenapp.di.networkModule
import com.kbtu.dukenapp.di.persistenceModule
import com.kbtu.dukenapp.di.repositoryModule
import com.kbtu.dukenapp.di.useCasesModule
import com.kbtu.dukenapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StoreApplication)
            modules(
                repositoryModule,
                useCasesModule,
                viewModelModule,
                networkModule,
                mapperModule,
                persistenceModule
            )
        }
    }
}
