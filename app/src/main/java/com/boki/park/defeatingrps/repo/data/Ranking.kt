package com.boki.park.defeatingrps.repo.data

data class Ranking(
    val id: Int,
    val order: Int,
    val userKey: String,
    val nick: String,
    val score: Int,
    val time: String
) {
    fun getOrder() = "$order"
    fun getScore() = "$score"
}