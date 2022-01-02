package com.boki.park.defeatingrps.room

import androidx.room.*


@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("user")
    )]
)
data class Record (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user", index = true) val user: Int,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "time") val time: Long
)


data class RecordWithUser (
    @Embedded
    val record: Record,
    @Relation(entity = User::class, parentColumn = "user", entityColumn = "uid")
    val user: User
)