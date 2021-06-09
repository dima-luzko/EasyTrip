package com.example.tickets.framework.remote

import com.example.tickets.framework.service.EasyTripService
import com.example.tickets.utils.Constants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val gson: Gson = GsonBuilder()
    .setLenient()
    .create()

object RemoteDataSource {
    val instance: EasyTripService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(EasyTripService::class.java)
}

