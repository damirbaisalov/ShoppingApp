package com.kbtu.dukenapp.data.model.user

import com.google.gson.annotations.SerializedName

data class UserRequestApiModel(
    val email: String,
    val password: String
)

data class UserApiModelResult(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("avatar") val avatar: String
)

data class LoginResult(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String
)

data class UserApiModel(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val avatar: String,
    val accessToken: String,
    val refreshToken: String
)

fun UserApiModelResult.toUserApiModel(loginResult: LoginResult): UserApiModel {
    return UserApiModel(
        id = id,
        email = email,
        name = name,
        password = password,
        avatar = avatar,
        accessToken = loginResult.accessToken,
        refreshToken = loginResult.refreshToken
    )
}

