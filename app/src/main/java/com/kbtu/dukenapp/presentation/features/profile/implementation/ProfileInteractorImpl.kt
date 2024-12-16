package com.kbtu.dukenapp.presentation.features.profile.implementation

import com.kbtu.dukenapp.data.model.ResponseResult
import com.kbtu.dukenapp.data.model.orders.toOrderUiModel
import com.kbtu.dukenapp.data.model.user.CreateUserRequest
import com.kbtu.dukenapp.data.model.user.UserRequestApiModel
import com.kbtu.dukenapp.data.model.user.toUserUiModel
import com.kbtu.dukenapp.domain.repository.AuthTokenRepository
import com.kbtu.dukenapp.domain.repository.UserRepository
import com.kbtu.dukenapp.presentation.features.profile.ProfileInteractor
import com.kbtu.dukenapp.presentation.features.profile.ProfileRouter
import com.kbtu.dukenapp.presentation.model.OrderUiModel
import com.kbtu.dukenapp.presentation.model.UserUiModel
import com.kbtu.dukenapp.presentation.model.toUiModel
import com.kbtu.dukenapp.presentation.mvi.CoroutineScopeContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ProfileInteractorImpl(
    val router: ProfileRouter,
    val repository: UserRepository,
    val authTokenRepository: AuthTokenRepository
) : ProfileInteractor, CoroutineScopeContainer() {

    override val ordersFlow: Flow<List<OrderUiModel>> = repository.ordersFlow
        .map { orders -> orders.map { it.toOrderUiModel() } }
        .flowOn(Dispatchers.Default)

    override fun logout() {
        authTokenRepository.logout()
    }

    override suspend fun getCurrentUser(): UserUiModel? {
        val userId = authTokenRepository.getUserId()
        val userUiModel = repository.loadUserFromDb(userId)

        return userUiModel
    }

    override suspend fun signIn(
        email: String,
        password: String,
        onSuccess: (UserUiModel) -> Unit,
        onError: (String) -> Unit
    ) {
        val userRequestApiModel = UserRequestApiModel(email, password)
        when (val responseResult = repository.login(userRequestApiModel)) {
            is ResponseResult.Error -> onError(
                responseResult.exception.message ?: "Something went wrong"
            )

            is ResponseResult.Success -> {
                authTokenRepository.setLoggedIn(
                    isLoggedIn = true,
                    accessToken = responseResult.data.accessToken,
                    userId = responseResult.data.id
                )
                onSuccess(responseResult.data.toUiModel())
            }
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        onSuccess: (UserUiModel) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val userCreateUserRequest = CreateUserRequest(
                name = name,
                email = email,
                password = password,
                avatar = "https://picsum.photos/800"
            )
            when (val responseResult = repository.createUser(userCreateUserRequest)) {
                is ResponseResult.Error -> onError(
                    responseResult.exception.message ?: "Something went wrong"
                )

                is ResponseResult.Success -> {

                    onSuccess(responseResult.data.toUserUiModel())
                }
            }
        } catch (e: Exception) {
            onError(e.message ?: "Something went wrong")
        }
    }
}