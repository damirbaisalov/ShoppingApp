package com.kbtu.dukenapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kbtu.dukenapp.data.model.orders.OrderStatus
import com.kbtu.dukenapp.presentation.model.CategoryUiModel

class Converters {

    // Convert List<String> to a comma-separated String
    @TypeConverter
    fun fromImagesList(images: List<String>): String {
        return images.joinToString(",") // Convert the list to a comma-separated String
    }

    // Convert String (CSV) back to List<String>
    @TypeConverter
    fun toImagesList(data: String): List<String> {
        return data.split(",") // Convert the CSV string back to List<String>
    }

    // Convert CategoryUiModel to a JSON String
    @TypeConverter
    fun fromCategory(category: CategoryUiModel): String {
        return Gson().toJson(category) // Convert CategoryUiModel to JSON String
    }

    // Convert a JSON String back to CategoryUiModel
    @TypeConverter
    fun toCategory(data: String): CategoryUiModel {
        return Gson().fromJson(data, CategoryUiModel::class.java) // Convert JSON String back to CategoryUiModel
    }

    @TypeConverter
    fun fromOrderStatus(status: OrderStatus): String {
        return status.status
    }

    @TypeConverter
    fun toOrderStatus(status: String): OrderStatus {
        return OrderStatus.valueOf(status.uppercase())
    }

    @TypeConverter
    fun fromProductIds(productIds: List<Int>): String {
        return productIds.joinToString(separator = ",") // Serialize as a comma-separated string
    }

    @TypeConverter
    fun toProductIds(productIds: String): List<Int> {
        return if (productIds.isEmpty()) emptyList() else productIds.split(",").map { it.toInt() }
    }
}
