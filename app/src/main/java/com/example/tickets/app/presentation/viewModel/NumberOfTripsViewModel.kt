package com.example.tickets.app.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfTrips
import com.example.tickets.app.data.model.NumberOfDaysOrTrips
import com.example.tickets.app.data.model.Price
import com.example.tickets.app.domain.repository.NumberOfTripsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumberOfTripsViewModel constructor(private val numberOfTripsRepository: NumberOfTripsRepository): ViewModel() {

    private val _numberOfTrips = MutableLiveData<List<NumberOfDaysOrTrips>>()
    val numberOfTrips: LiveData<List<NumberOfDaysOrTrips>> = _numberOfTrips

    private val _price = MutableLiveData<Price>()
    val price: LiveData<Price> = _price

    fun getNumberOfTrips() {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfTrips = numberOfTripsRepository.getNumberOfTrips()
            _numberOfTrips.postValue(numberOfTrips)
        }
    }

    fun getPrice(body: BodyForGetPriceByNumberOfTrips){
        viewModelScope.launch(Dispatchers.IO) {
            val price = numberOfTripsRepository.getPrice(body)
            _price.postValue(price)
        }
    }
}