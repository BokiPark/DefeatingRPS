package com.boki.park.defeatingrps.network.data

data class Record(
    val id: Int,
    val userKey: String,
    val nick: String,
    val score: Int,
    val time: String
)