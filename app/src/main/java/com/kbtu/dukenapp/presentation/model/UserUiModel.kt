package com.kbtu.dukenapp.presentation.model

import com.kbtu.dukenapp.data.model.user.UserApiModel
import com.kbtu.dukenapp.data.model.user.UserDBModel

data class UserUiModel(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val avatar: String
) {
    companion object {
        fun empty(): UserUiModel {
            return UserUiModel(
                id = 0,
                email = "test@gmail.com",
                password = "1321321",
                name = "Test",
                avatar = "https://i.imgur.com/yhW6Yw1.jpg"
            )
        }
    }
}

fun UserDBModel.toUiModel(): UserUiModel {
    return UserUiModel(
        id = userIdFromNetwork,
        email = email,
        password = password,
        name = name,
        avatar = avatar
    )
}

fun UserApiModel.toUiModel(): UserUiModel {
    return UserUiModel(
        id = id,
        email = email,
        password = password,
        name = name,
        avatar = avatar
    )
}