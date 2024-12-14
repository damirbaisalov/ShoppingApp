package com.kbtu.dukenapp.data.model.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.kbtu.dukenapp.data.model.Converters
import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.presentation.model.ProductUiModel

//@Entity(tableName = "cart_items")
//data class CartItemDBModel(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    @ColumnInfo(name = "product_id") val productId: Int,
//    @ColumnInfo(name = "quantity") val quantity: Int
//)

@Entity(tableName = "cart_items")
data class CartItemDBModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generated ID for cart item
    @ColumnInfo(name = "product_id") val productId: Int, // Product ID
    @ColumnInfo(name = "name") val name: String, // Product name
    @ColumnInfo(name = "description") val description: String, // Product description
    @ColumnInfo(name = "price") val price: Double, // Product price
    @ColumnInfo(name = "images") val images: String, // Product images (List converted to String)
    @ColumnInfo(name = "category") val category: String, // Category (could be stored as a String or JSON)
    @ColumnInfo(name = "count") val count: Int = 0 // Quantity in the cart
)

fun ProductUiModel.toCartItemDBModel(): CartItemDBModel {
    return CartItemDBModel(
        productId = this.productId,
        name = this.name,
        description = this.description,
        price = this.price,
        images = this.images.joinToString(","), // Convert List<String> to a comma-separated string
        category = Gson().toJson(this.category), // Assuming category is a CategoryUiModel and you want its name
        count = this.count
    )
}

fun CartItemDBModel.toProductUiModel(): ProductUiModel {
    val category = Gson().fromJson(this.category, CategoryUiModel::class.java) // Convert JSON to CategoryUiModel
    return ProductUiModel(
        productId = this.productId,
        name = this.name,
        description = this.description,
        price = this.price,
        images = this.images.split(","), // Convert comma-separated string back to List<String>
        category = category,
        count = this.count
    )
}

