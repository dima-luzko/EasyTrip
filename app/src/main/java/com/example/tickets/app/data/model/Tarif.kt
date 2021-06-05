package com.example.tickets.app.data.model

import com.google.gson.annotations.SerializedName

data class Tarif(
    @SerializedName("id")
    val id : Int,
    @SerializedName("transports")
    val transports: List<Transports>,
    @SerializedName("number_of_day_id")
    val numberOfDays: NumberOfDaysOrTrips?,
    @SerializedName("price")
    val price: Double,
    @SerializedName("number_of_trip_id")
    val numberOfTrips: Int
)
