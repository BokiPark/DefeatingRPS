package com.boki.park.defeatingrps.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun get(): Flow<List<User>>

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}