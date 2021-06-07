package com.example.tickets.app.domain.repository

import com.example.tickets.app.data.model.NumberOfDaysOrTrips

interface NumberOfDaysRepository {
    suspend fun getNumberOfDays(): List<NumberOfDaysOrTrips>
}