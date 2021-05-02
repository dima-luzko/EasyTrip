package com.example.tickets.data

import androidx.annotation.DrawableRes

data class TransportInfo(
    val transportName: String,
    @DrawableRes val transportImg : Int,
    @DrawableRes val statusImg : Int,
    val statusText: String
)
