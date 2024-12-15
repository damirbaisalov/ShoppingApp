package com.kbtu.dukenapp.data.model.user

import com.kbtu.dukenapp.presentation.model.UserUiModel

data class CreateUserRequest(
    val name: String,
    val email: String,
    val password: String,
    val avatar: String
)

data class CreateUserResponse(
    val email: String,
    val password: String,
    val name: String,
    val avatar: String,
    val role: String,
    val id: Int
)

fun CreateUserResponse.toUserUiModel(): UserUiModel {
    return UserUiModel(
        id = id,
        name = name,
        email = email,
        avatar = avatar,
        password = password,
//        orderHistory = emptyList()
    )
}
