package com.kbtu.dukenapp.di

import com.kbtu.dukenapp.data.repository.AuthTokenRepositoryImpl
import com.kbtu.dukenapp.data.repository.AuthorizationRepositoryImpl
import com.kbtu.dukenapp.data.repository.OnlineStoreRepositoryImpl
import com.kbtu.dukenapp.domain.repository.AuthTokenRepository
import com.kbtu.dukenapp.domain.repository.AuthorizationRepository
import com.kbtu.dukenapp.domain.repository.OnlineStoreRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factoryOf(::AuthorizationRepositoryImpl).bind(AuthorizationRepository::class)
    factoryOf(::AuthTokenRepositoryImpl).bind(AuthTokenRepository::class)
    factoryOf(::OnlineStoreRepositoryImpl).bind(OnlineStoreRepository::class)
}