package com.kbtu.dukenapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kbtu.dukenapp.data.model.user.UserDBModel

@Dao
interface UserDao {
    // Insert a user
    @Insert
    suspend fun insertUser(user: UserDBModel)

    // Get a user by ID
    @Query("SELECT * FROM users WHERE userIdFromNetwork = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): UserDBModel?

    // Get all users
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserDBModel>

    // Update a user
    @Update
    suspend fun updateUser(user: UserDBModel)

    // Delete a user
    @Delete
    suspend fun deleteUser(user: UserDBModel)
}