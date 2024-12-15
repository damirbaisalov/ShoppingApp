package com.kbtu.dukenapp.data.repository

import com.kbtu.dukenapp.data.api.UserService
import com.kbtu.dukenapp.data.local.OrderDao
import com.kbtu.dukenapp.data.local.UserDao
import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.orders.toOrderUiModel
import com.kbtu.dukenapp.data.model.user.CreateUserRequest
import com.kbtu.dukenapp.data.model.user.CreateUserResponse
import com.kbtu.dukenapp.data.model.user.UserApiModel
import com.kbtu.dukenapp.data.model.user.UserDBModel
import com.kbtu.dukenapp.data.model.user.UserRequestApiModel
import com.kbtu.dukenapp.data.model.user.toUserApiModel
import com.kbtu.dukenapp.domain.repository.UserRepository
import com.kbtu.dukenapp.presentation.model.OrderUiModel
import com.kbtu.dukenapp.presentation.model.UserUiModel
import com.kbtu.dukenapp.presentation.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userService: UserService,
    private val userDao: UserDao,
    private val orderDao: OrderDao,
) : UserRepository {

    override val ordersFlow: Flow<List<OrderUiModel>> = orderDao.getOrdersFlow()
        .map { orders -> orders.map { it.toOrderUiModel() } }
        .flowOn(Dispatchers.Default)


    override suspend fun loadUserFromDb(userId: Int): UserUiModel {
        return try {
            withContext(Dispatchers.IO) {
                return@withContext userDao.getUserById(userId)?.toUiModel() ?: UserUiModel.empty()
            }
        } catch (e: Exception) {
            UserUiModel.empty()
        }
    }

    override suspend fun login(userRequestApiModel: UserRequestApiModel): ResponseResult<UserApiModel> {
        return try {
            withContext(Dispatchers.IO) {
                val loginResult = userService.login(userRequestApiModel)
                val userApiModelResult =
                    userService.getProfile(accessToken = "Bearer ${loginResult.accessToken}")
                userDao.insertUser(
                    UserDBModel(
                        userIdFromNetwork = userApiModelResult.id,
                        avatar = userApiModelResult.avatar,
                        name = userApiModelResult.name,
                        password = userApiModelResult.password,
                        email = userApiModelResult.email
                    )
                )
                return@withContext ResponseResult.Success(
                    userApiModelResult.toUserApiModel(
                        loginResult
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseResult.Error(e)
        }
    }

    override suspend fun createUser(userCreateUserRequest: CreateUserRequest): ResponseResult<CreateUserResponse> {
        return try {
            withContext(Dispatchers.IO) {
                val createdUser = userService.createUser(userCreateUserRequest)
                val user = UserDBModel(
                    userIdFromNetwork = createdUser.id,
                    email = createdUser.email,
                    name = createdUser.name,
                    password = createdUser.password,
                    avatar = createdUser.avatar
                )
                userDao.insertUser(user)
                return@withContext ResponseResult.Success(createdUser)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseResult.Error(e)
        }
    }
}