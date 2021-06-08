package com.example.tickets.app.domain.repository

import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfTrips
import com.example.tickets.app.data.model.NumberOfDaysOrTrips
import com.example.tickets.app.data.model.Price

interface NumberOfTripsRepository {
    suspend fun getNumberOfTrips(): List<NumberOfDaysOrTrips>
    suspend fun getPrice(body: BodyForGetPriceByNumberOfTrips): Price
}