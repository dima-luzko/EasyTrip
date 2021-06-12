package com.example.tickets.app.data.model

import com.google.gson.annotations.SerializedName

data class BodyForGetPriceByNumberOfTrips(
    @SerializedName("number_of_trip_id")
    val numberOfTripsId : Int?,
    @SerializedName("transports")
    val transports: ArrayList<Int>,
    @SerializedName("count")
    val count: Int
)
