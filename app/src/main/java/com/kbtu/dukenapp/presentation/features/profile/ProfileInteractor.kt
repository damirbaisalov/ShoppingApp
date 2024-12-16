package com.kbtu.dukenapp.presentation.features.profile

import com.kbtu.dukenapp.presentation.model.OrderUiModel
import com.kbtu.dukenapp.presentation.model.UserUiModel
import com.kbtu.dukenapp.presentation.mvi.BaseInteractor
import kotlinx.coroutines.flow.Flow

interface ProfileInteractor : BaseInteractor<ProfileRouter> {

    val ordersFlow: Flow<List<OrderUiModel>>

    fun logout()

    suspend fun getCurrentUser(): UserUiModel?

    suspend fun signIn(
        email: String,
        password: String,
        onSuccess: (UserUiModel) -> Unit,
        onError: (String) -> Unit
    )

    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        onSuccess: (UserUiModel) -> Unit,
        onError: (String) -> Unit
    )
}