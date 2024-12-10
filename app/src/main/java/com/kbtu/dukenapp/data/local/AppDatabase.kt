package com.kbtu.dukenapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kbtu.dukenapp.data.model.user.UserDBModel

@Database(entities = [UserDBModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun onlineStoreDao(): AuthorizationDao
}