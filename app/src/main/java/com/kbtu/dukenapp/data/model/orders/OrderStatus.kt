package com.kbtu.dukenapp.data.model.orders

enum class OrderStatus(val status: String) {
    PENDING("pending"),
    COMPLETED("completed"),
    CANCELLED("cancelled")
}
