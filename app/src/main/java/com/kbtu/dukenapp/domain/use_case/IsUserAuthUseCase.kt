package com.kbtu.dukenapp.domain.use_case

import com.kbtu.dukenapp.domain.repository.AuthTokenRepository


class IsUserAuthUseCase(private val authRepository: AuthTokenRepository) {

    operator fun invoke(): Boolean {
        return authRepository.isUserAuth()
    }
}