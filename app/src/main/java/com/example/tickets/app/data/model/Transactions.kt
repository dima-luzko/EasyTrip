package com.example.tickets.app.data.model

import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.util.*

data class Transactions(
    @SerializedName("id")
    val id : Int,
    @SerializedName("tarif_id")
    val tarif: Tarif,
    @SerializedName("start_data")
    val startData: String,
    @SerializedName("finish_data")
    val finishData: String,
    @SerializedName("number_of_trip_left")
    val numberOfTripLeft: Int?,
    @SerializedName("card_id")
    val card_id: Int
)
