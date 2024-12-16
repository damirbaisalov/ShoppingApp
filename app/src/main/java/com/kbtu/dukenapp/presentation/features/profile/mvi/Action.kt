package com.kbtu.dukenapp.presentation.features.profile.mvi

import com.common.mvi.BaseAction
import com.kbtu.dukenapp.presentation.model.OrderUiModel
import com.kbtu.dukenapp.presentation.model.UserUiModel

sealed class Action: BaseAction() {

    class SetScreenState(val screenState: ProfileScreenState): Action()
    class SetUser(val userUiModel: UserUiModel?): Action()
    class SetOrders(val orders: List<OrderUiModel>): Action()
}