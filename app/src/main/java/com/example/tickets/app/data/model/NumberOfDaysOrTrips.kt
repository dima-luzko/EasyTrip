package com.example.tickets.app.data.model

import com.google.gson.annotations.SerializedName

data class NumberOfDaysOrTrips(
    @SerializedName("id")
    val id : Int,
    @SerializedName("value")
    val value: Int
)
