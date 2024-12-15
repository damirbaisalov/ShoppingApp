package com.kbtu.dukenapp.domain.repository

import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.user.CreateUserRequest
import com.kbtu.dukenapp.data.model.user.CreateUserResponse
import com.kbtu.dukenapp.data.model.user.UserApiModel
import com.kbtu.dukenapp.data.model.user.UserRequestApiModel
import com.kbtu.dukenapp.presentation.model.OrderUiModel
import com.kbtu.dukenapp.presentation.model.UserUiModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val ordersFlow: Flow<List<OrderUiModel>>

    suspend fun loadUserFromDb(userId: Int): UserUiModel
    suspend fun createUser(userCreateUserRequest: CreateUserRequest): ResponseResult<CreateUserResponse>
    suspend fun login(userRequestApiModel: UserRequestApiModel): ResponseResult<UserApiModel>
}