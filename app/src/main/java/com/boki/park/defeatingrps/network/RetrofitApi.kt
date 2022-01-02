package com.boki.park.defeatingrps.network

import com.boki.park.defeatingrps.network.data.Record
import com.boki.park.defeatingrps.network.response.RecordsResponse
import com.boki.park.defeatingrps.network.response.RetrofitResponse
import retrofit2.http.*

interface RetrofitApi {

    @GET("record/all")
    suspend fun getRecords(): RecordsResponse

    @GET("record/my")
    suspend fun getMyRecords(
        @Query("userKey") userKey: String
    ): RecordsResponse

    @POST("record/my")
    suspend fun addRecord(
        @Body record: Record
    ): RetrofitResponse
}