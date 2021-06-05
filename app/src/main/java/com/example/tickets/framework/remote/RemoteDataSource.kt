package com.example.tickets.framework.remote

import com.example.tickets.framework.service.EasyTripService
import com.example.tickets.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    val instance: EasyTripService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EasyTripService::class.java)
}