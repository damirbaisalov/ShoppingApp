package com.kbtu.dukenapp.data.api

import com.kbtu.dukenapp.data.model.user.CreateUserRequest
import com.kbtu.dukenapp.data.model.user.CreateUserResponse
import com.kbtu.dukenapp.data.model.user.LoginResult
import com.kbtu.dukenapp.data.model.user.UserRequestApiModel
import com.kbtu.dukenapp.data.model.user.UserApiModelResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("users/")
    suspend fun createUser(
        @Body user: CreateUserRequest
    ): CreateUserResponse

    @POST("auth/login")
    suspend fun login(
        @Body user: UserRequestApiModel
    ): LoginResult

    @GET("auth/profile")
    suspend fun getProfile(
        @Header("Authorization") accessToken: String
    ): UserApiModelResult
}