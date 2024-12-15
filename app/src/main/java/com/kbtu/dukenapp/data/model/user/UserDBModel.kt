package com.kbtu.dukenapp.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(
//    tableName = USERS
//)
//data class UserDBModel(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//    val name: String,
//    val email: String,
//    val password: String
//)

@Entity(tableName = "users")
data class UserDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userIdFromNetwork: Int,
    val email: String,
    val password: String,
    val name: String,
    val avatar: String
)