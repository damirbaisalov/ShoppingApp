package com.kbtu.dukenapp.presentation.model

import com.kbtu.dukenapp.data.model.orders.OrderStatus

data class OrderUiModel(
    val orderId: Int,
    val status: OrderStatus,
    val totalPrice: Double,
    val createdDate: String
)