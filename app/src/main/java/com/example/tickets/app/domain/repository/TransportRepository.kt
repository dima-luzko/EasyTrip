package com.example.tickets.app.domain.repository

import com.example.tickets.app.data.model.Transports

interface TransportRepository {
    suspend fun getTransports(): List<Transports>
    suspend fun getTransportById(transportId: Int): Transports
}