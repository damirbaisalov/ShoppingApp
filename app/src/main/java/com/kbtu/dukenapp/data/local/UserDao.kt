package com.kbtu.dukenapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kbtu.dukenapp.data.model.user.UserDBModel

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserDBModel)

    @Query("SELECT * FROM users WHERE userIdFromNetwork = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): UserDBModel?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserDBModel>

    @Update
    suspend fun updateUser(user: UserDBModel)

    @Delete
    suspend fun deleteUser(user: UserDBModel)
}