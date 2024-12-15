package com.kbtu.dukenapp.domain.repository

interface AuthTokenRepository {

    fun getAuthToken(): Int
    fun storeAuthToken(token: Int)
    fun clearAuthToken()
    fun isUserAuth(): Boolean

    fun isLoggedIn(): Boolean
    fun setLoggedIn(isLoggedIn: Boolean, accessToken: String, userId: Int)
    fun getAccessToken(): String?
    fun getUserId(): Int
    fun logout()
}