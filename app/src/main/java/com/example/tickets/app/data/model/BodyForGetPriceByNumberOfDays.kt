package com.example.tickets.app.data.model

data class BodyForGetPriceByNumberOfDays(
    val numberOfDaysId : Int,
    val transports: Transports,
    val count: Int
)
