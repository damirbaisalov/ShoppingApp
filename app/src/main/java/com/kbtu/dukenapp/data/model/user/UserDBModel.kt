package com.kbtu.dukenapp.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kbtu.dukenapp.utils.Constants.USERS

@Entity(
    tableName = USERS
)
data class UserDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val password: String
)