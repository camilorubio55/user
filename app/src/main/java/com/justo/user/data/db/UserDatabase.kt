package com.justo.user.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justo.user.data.db.dao.UserDao
import com.justo.user.data.db.entities.UserDB

@Database(
    entities = [
        UserDB::class
    ],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}