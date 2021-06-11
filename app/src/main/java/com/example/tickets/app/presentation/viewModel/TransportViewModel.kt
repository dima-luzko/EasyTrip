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

    private var transports: List<Transports> = listOf()


    private suspend fun getBus(): String {
        transports = transportRepository.getTransports()
        return transports.map { it.transportName }[0]
    }

    private suspend fun getTrolleybus(): String {
        transports = transportRepository.getTransports()
        return transports.map { it.transportName }[1]
    }

    private suspend fun getTram(): String {
        transports = transportRepository.getTransports()
        return transports.map { it.transportName }[2]
    }

    fun getCombineTransport() {
        viewModelScope.launch(Dispatchers.IO) {
            val firstTransport = getBus()
            val secondTransport = getTrolleybus()
            val thirdTransport = getTram()
            val combineTransports = "$firstTransport - $secondTransport - $thirdTransport"
            _transportName.postValue(combineTransports)
        }
    }

    fun getBusExpress(): String {
        var transports: List<Transports>?
        var busExpress = ""
        viewModelScope.launch(Dispatchers.IO) {
            transports = transportRepository.getTransports()
            busExpress = transports!!.map { it.transportName }[3].substring(0, 12)
            _transportName.postValue(busExpress)
        }
        return busExpress
    }

    fun getMetro(): String {
        var transports: List<Transports>?
        var metro = ""
        viewModelScope.launch(Dispatchers.IO) {
            transports = transportRepository.getTransports()
            metro = transports!!.map { it.transportName }[4]
            _transportName.postValue(metro)
        }
        return metro
    }

}