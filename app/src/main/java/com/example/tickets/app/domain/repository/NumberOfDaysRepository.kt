package com.example.tickets.app.domain.repository

import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfDays
import com.example.tickets.app.data.model.NumberOfDaysOrTrips
import com.example.tickets.app.data.model.Price

interface NumberOfDaysRepository {
    suspend fun getNumberOfDays(): List<NumberOfDaysOrTrips>
    suspend fun getPrice(body: BodyForGetPriceByNumberOfDays): Price
}