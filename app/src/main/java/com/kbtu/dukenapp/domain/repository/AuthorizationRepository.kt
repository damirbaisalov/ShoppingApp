package com.kbtu.dukenapp.domain.repository

import com.kbtu.dukenapp.domain.model.User


interface AuthorizationRepository {

    suspend fun checkUser(email: String): Boolean

    suspend fun createUser(user: User)

    suspend fun loginUser(email: String, password: String): Int?

    suspend fun getUserByTokenId(id: Int): User?
}