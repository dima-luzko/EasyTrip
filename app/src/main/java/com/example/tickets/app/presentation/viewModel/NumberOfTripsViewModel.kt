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

class NumberOfTripsViewModel constructor(private val numberOfTripsRepository: NumberOfTripsRepository) :
    ViewModel() {

    private val _numberOfTrips = MutableLiveData<List<NumberOfDaysOrTrips>>()
    val numberOfTrips: LiveData<List<NumberOfDaysOrTrips>> = _numberOfTrips

    private val _numberOfTripsForMetro = MutableLiveData<List<NumberOfDaysOrTrips>>()
    val numberOfTripsForMetro: LiveData<List<NumberOfDaysOrTrips>> = _numberOfTripsForMetro

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    var numberOfTripsList: List<NumberOfDaysOrTrips> = listOf()

    fun getNumberOfTrips() {
        viewModelScope.launch(Dispatchers.IO) {
            numberOfTripsList = numberOfTripsRepository.getNumberOfTrips()
            _numberOfTrips.postValue(numberOfTripsList)
        }
    }

    fun getNumberOfTripsForMetro() {
        viewModelScope.launch(Dispatchers.IO) {
            numberOfTripsList = numberOfTripsRepository.getNumberOfTrips()
            _numberOfTripsForMetro.postValue(numberOfTripsList)
        }
    }

    fun getPrice(
        body: BodyForGetPriceByNumberOfTrips,
        body2: BodyForGetPriceByNumberOfTrips,
        body3: BodyForGetPriceByNumberOfTrips
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result1 = getPriceForItem(body)
            val result2 = getPriceForItem(body2)
            val result3 = getPriceForItem(body3)
            val sum = result1 + result2 + result3
            _price.postValue(sum)
        }
    }

    private suspend fun getPriceForItem(body: BodyForGetPriceByNumberOfTrips): Double {
        val price: Price = numberOfTripsRepository.getPrice(body)
        return price.price
    }


}