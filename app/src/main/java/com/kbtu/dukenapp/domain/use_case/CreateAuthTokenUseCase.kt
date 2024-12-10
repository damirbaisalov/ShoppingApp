package com.kbtu.dukenapp.domain.use_case

import com.kbtu.dukenapp.domain.repository.AuthTokenRepository

class CreateAuthTokenUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(token: Int) {
        return authRepository.storeAuthToken(token)
    }
}