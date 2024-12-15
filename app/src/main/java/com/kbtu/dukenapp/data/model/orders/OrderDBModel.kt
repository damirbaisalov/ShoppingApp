package com.kbtu.dukenapp.data.model.orders

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo
import com.kbtu.dukenapp.data.model.user.UserDBModel
import com.kbtu.dukenapp.presentation.model.OrderUiModel

@Entity(
    tableName = "orders",
    foreignKeys = [
        ForeignKey(
            entity = UserDBModel::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrderDBModel(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0,
    val userId: Int,  // Foreign key to User
    val status: OrderStatus,
    @ColumnInfo(name = "total_price")
    val totalPrice: Double,
    @ColumnInfo(name = "created_date")
    val createdDate: String,
    @ColumnInfo(name = "product_ids")
    val productIds: String
)

fun OrderDBModel.toOrderUiModel(): OrderUiModel {
    return OrderUiModel(
        orderId = orderId,
        status = status,
        createdDate = createdDate,
        totalPrice = totalPrice
    )
}
