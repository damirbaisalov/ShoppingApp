package com.kbtu.dukenapp.di

import com.kbtu.dukenapp.domain.use_case.CreateAuthTokenUseCase
import com.kbtu.dukenapp.domain.use_case.GetCharactersUseCase
import com.kbtu.dukenapp.domain.use_case.ValidateEmail
import com.kbtu.dukenapp.domain.use_case.ValidateField
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val useCasesModule: Module = module {
    factoryOf(::ValidateEmail)
    factoryOf(::ValidateField)
    factoryOf(::CreateAuthTokenUseCase)
    factoryOf(::GetCharactersUseCase)
}


