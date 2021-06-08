package com.example.tickets.app.data.repositoryImpl

import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfTrips
import com.example.tickets.app.data.model.NumberOfDaysOrTrips
import com.example.tickets.app.data.model.Price
import com.example.tickets.app.domain.repository.NumberOfTripsRepository
import com.example.tickets.framework.remote.RemoteDataSource

class NumberOfTripsRepositoryImpl (private val dataSource: RemoteDataSource): NumberOfTripsRepository {
    override suspend fun getNumberOfTrips(): List<NumberOfDaysOrTrips> {
        return dataSource.instance.getNumberOfTripsList()
    }

    override suspend fun getPrice(body: BodyForGetPriceByNumberOfTrips): Price {
        return dataSource.instance.getPriceByNumberOfTripsAndTransports(getPrice = body)
    }
}