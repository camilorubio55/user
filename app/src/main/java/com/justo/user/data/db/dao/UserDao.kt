package com.justo.user.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.justo.user.data.db.entities.UserDB
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUsers(listUsers : List<UserDB>)

    @Query("SELECT * FROM user")
    fun getUsers() : Flow<List<UserDB>>

    @Query("UPDATE user SET is_selectable = :status WHERE 1 = 1")
    suspend fun updateUserSelectionStatus(status : Int)

}