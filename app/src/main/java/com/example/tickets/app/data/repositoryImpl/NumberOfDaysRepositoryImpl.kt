package com.example.tickets.app.data.repositoryImpl

import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfDays
import com.example.tickets.app.data.model.NumberOfDaysOrTrips
import com.example.tickets.app.data.model.Price
import com.example.tickets.app.domain.repository.NumberOfDaysRepository
import com.example.tickets.framework.remote.RemoteDataSource

class NumberOfDaysRepositoryImpl(private val dataSource: RemoteDataSource): NumberOfDaysRepository {
    override suspend fun getNumberOfDays(): List<NumberOfDaysOrTrips> {
        return dataSource.instance.getNumberOfDaysList()
    }

    override suspend fun getPrice(body: BodyForGetPriceByNumberOfDays): Price {
        return dataSource.instance.getPriceByNumberOfDaysAndTransports(getPrice = body)
    }
}