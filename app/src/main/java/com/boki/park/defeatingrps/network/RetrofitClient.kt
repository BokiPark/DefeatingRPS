package com.boki.park.defeatingrps.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    val instance by lazy {
        Retrofit.Builder()
            .baseUrl("http://54.202.152.159:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<RetrofitApi>()
    }
}