package com.example.tickets.app.data.repositoryImpl

import com.example.tickets.app.data.model.Transports
import com.example.tickets.app.domain.repository.TransportRepository
import com.example.tickets.framework.remote.RemoteDataSource

class TransportRepositoryImpl(private val dataSource: RemoteDataSource): TransportRepository {
    override suspend fun getTransports(): List<Transports> {
        return dataSource.instance.getTransportsList()
    }

    override suspend fun getTransportById(transportId: Int): Transports {
        return dataSource.instance.getTransportsById(transportId = transportId)
    }
}