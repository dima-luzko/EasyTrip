package com.example.tickets.app.data.model

import com.google.gson.annotations.SerializedName

data class BodyForGetPriceByNumberOfDays(
    @SerializedName("number_of_day_id")
    val numberOfDaysId : Int,
    @SerializedName("transports")
    val transports: ArrayList<Int>,
    @SerializedName("count")
    val count: Int
)
