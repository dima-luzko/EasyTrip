package com.example.tickets.app.data.model

data class BodyForGetPriceByNumberOfTrips(
    val numberOfTripsId : Int,
    val transports: Transports,
    val count: Int
)
