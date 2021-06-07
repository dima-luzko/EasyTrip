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

    private val _transport = MutableLiveData<List<Transports>>()
    val transport: LiveData<List<Transports>> = _transport

    private val _transportById = MutableLiveData<Transports>()
    val transportById: LiveData<Transports> = _transportById

    fun getTransport() {
        viewModelScope.launch(Dispatchers.IO) {
            val transports = transportRepository.getTransports()
            _transport.postValue(transports)
        }
    }

    fun getTransportById(transportId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val transportsById = transportRepository.getTransportById(transportId)
            _transportById.postValue(transportsById)
        }
    }

}