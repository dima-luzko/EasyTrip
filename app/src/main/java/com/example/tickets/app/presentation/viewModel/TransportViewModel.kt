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

    private val _transportName = MutableLiveData<String>()
    val transportName: LiveData<String> = _transportName

    private val _transportById = MutableLiveData<Transports>()
    val transportById: LiveData<Transports> = _transportById

    private var busExpress: String = ""
    private var metro: String = ""

    private fun getBuss(): String {
        var transports: List<Transports>?
        var bus  = ""
        viewModelScope.launch(Dispatchers.IO) {
            transports = transportRepository.getTransports()
            bus = transports!!.map { it.transportName }[0]
        }
        return bus
    }

    private fun getTroll(): String {
        var transports: List<Transports>?
        var trolleybus = ""
        viewModelScope.launch(Dispatchers.IO) {
            transports = transportRepository.getTransports()
            trolleybus = transports!!.map { it.transportName }[1]
        }
        return trolleybus
    }

    private fun getTram(): String {
        var transports: List<Transports>?
        var tram = ""
        viewModelScope.launch(Dispatchers.IO) {
            transports = transportRepository.getTransports()
            tram = transports!!.map { it.transportName }[2]
        }
        return tram
    }

    fun getCombineTransport() {
        val firstTransport = getBuss()
        val secondTransport = getTroll()
        val thirdTransport = getTram()
        val combineTransports = "$firstTransport - $secondTransport - $thirdTransport"
        _transportName.postValue(combineTransports)
    }

    fun getBusExpress() {
        viewModelScope.launch(Dispatchers.IO) {
            val transports = transportRepository.getTransports()
            busExpress = transports.map { it.transportName }[3].substring(0, 12)
            _transportName.postValue(busExpress)
        }
    }

    fun getMetro() {
        viewModelScope.launch(Dispatchers.IO) {
            val transports = transportRepository.getTransports()
            metro = transports.map { it.transportName }[4]
            _transportName.postValue(metro)
        }
    }


    fun getTransportById(transportId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val transportsById = transportRepository.getTransportById(transportId)
            _transportById.postValue(transportsById)
        }
    }
}