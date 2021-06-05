package com.example.tickets.app.data.model

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("id")
    val id : Int,
    @SerializedName("number")
    val cardNumber: String
)
