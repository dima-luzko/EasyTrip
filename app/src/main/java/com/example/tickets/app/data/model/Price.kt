package com.example.tickets.app.data.model

import com.google.gson.annotations.SerializedName

data class Price (
    @SerializedName("price")
    val price : Double
)