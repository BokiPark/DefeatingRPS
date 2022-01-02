package com.boki.park.defeatingrps.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM record ORDER BY score DESC")
    fun get(): Flow<List<RecordWithUser>>

    @Insert
    fun insert(record: Record)

    @Delete
    fun delete(record: Record)
}