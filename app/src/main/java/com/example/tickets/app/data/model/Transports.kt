package com.example.tickets.app.data.model

import com.google.gson.annotations.SerializedName

data class Transports(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val transportName: String,
    var isPressed: Boolean = false
)
