package com.example.tickets.app.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.app.data.model.Transports
import com.example.tickets.app.domain.repository.TransportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransportViewModel constructor(private val transportRepository: TransportRepository) :
    ViewModel() {

    private val _transportName = MutableLiveData<TransportsStorage>()
    val transportName: LiveData<TransportsStorage> = _transportName

    private val _transport = MutableLiveData<List<Transports>>()
    val transport: LiveData<List<Transports>> = _transport

    var transportsList: List<Transports> = listOf()

    data class TransportsStorage(
        val bus: String,
        val trolleybus: String,
        val tram: String,
        val busExpressShort: String,
        val busExpress: String,
        val metro: String,
        val train_city_lines: String
    )

    fun getTransportList() {
        viewModelScope.launch(Dispatchers.IO) {
            transportsList = transportRepository.getTransports()
            _transport.postValue(transportsList)
        }
    }

    fun getTransportName() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = setTransportStorage()
            _transportName.postValue(result)
        }
    }

    private suspend fun setTransportStorage(): TransportsStorage {
        val transportsList = transportRepository.getTransports()
        return TransportsStorage(
            bus = transportsList.map { it.transportName }[0],
            trolleybus = transportsList.map { it.transportName }[1],
            tram = transportsList.map { it.transportName }[2],
            busExpressShort = transportsList.map { it.transportName }[3].substring(0, 12),
            busExpress = transportsList.map { it.transportName }[3],
            metro = transportsList.map { it.transportName }[4],
            train_city_lines = transportsList.map { it.transportName }[5]
        )
    }
}