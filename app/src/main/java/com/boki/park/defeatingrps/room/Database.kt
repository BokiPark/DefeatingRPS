package com.boki.park.defeatingrps.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Record::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun recordDao(): RecordDao
}