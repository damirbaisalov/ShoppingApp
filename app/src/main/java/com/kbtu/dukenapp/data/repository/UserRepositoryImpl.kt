package com.kbtu.dukenapp.data.repository

import com.kbtu.dukenapp.data.api.UserService
import com.kbtu.dukenapp.data.local.OrderDao
import com.kbtu.dukenapp.data.local.UserDao
import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.orders.OrderDBModel
import com.kbtu.dukenapp.data.model.user.CreateUserRequest
import com.kbtu.dukenapp.data.model.user.CreateUserResponse
import com.kbtu.dukenapp.data.model.user.UserApiModel
import com.kbtu.dukenapp.data.model.user.UserDBModel
import com.kbtu.dukenapp.data.model.user.UserRequestApiModel
import com.kbtu.dukenapp.data.model.user.toUserApiModel
import com.kbtu.dukenapp.domain.repository.UserRepository
import com.kbtu.dukenapp.presentation.model.UserUiModel
import com.kbtu.dukenapp.presentation.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userService: UserService,
    private val userDao: UserDao,
    private val orderDao: OrderDao,
) : UserRepository {

    private val currentUserId = MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val ordersFlow: Flow<List<OrderDBModel>> = currentUserId
        .filterNotNull()
        .flatMapLatest { userId ->
            orderDao.getOrdersByUserIdFlow(userId)
        }

    private fun setCurrentUser(userId: Int) {
        currentUserId.value = userId
    }

    override suspend fun loadUserFromDb(userId: Int): UserUiModel? {
        return try {
            withContext(Dispatchers.IO) {
                val user = userDao.getUserById(userId)
                user?.let { setCurrentUser(it.id) }
                user?.toUiModel()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun login(userRequestApiModel: UserRequestApiModel): ResponseResult<UserApiModel> {
        return try {
            withContext(Dispatchers.IO) {
                val loginResult = userService.login(userRequestApiModel)
                val userApiModelResult =
                    userService.getProfile(accessToken = "Bearer ${loginResult.accessToken}")
                val user = userDao.getAllUsers().find { it.userIdFromNetwork == userApiModelResult.id }
                if (user == null) {
                    userDao.insertUser(
                        UserDBModel(
                            userIdFromNetwork = userApiModelResult.id,
                            avatar = userApiModelResult.avatar,
                            name = userApiModelResult.name,
                            password = userApiModelResult.password,
                            email = userApiModelResult.email
                        )
                    )
                }
                val insertedUser = userDao.getUserById(userApiModelResult.id)
                insertedUser?.let { setCurrentUser(it.id) }
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