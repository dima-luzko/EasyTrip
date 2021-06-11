package com.example.tickets.app.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfTrips
import com.example.tickets.app.data.model.Price
import com.example.tickets.app.domain.repository.NumberOfTripsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumberOfTripsViewModel constructor(private val numberOfTripsRepository: NumberOfTripsRepository) :
    ViewModel() {

    private val _numberOfTrips = MutableLiveData<Int>()
    val numberOfTrips: LiveData<Int> = _numberOfTrips

    private val _numberOfTripsForMetro = MutableLiveData<Int>()
    val numberOfTripsForMetro: LiveData<Int> = _numberOfTripsForMetro

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    var numberOfTripsList: List<Int> = listOf()

    fun getNumberOfTrips() {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfTrips = numberOfTripsRepository.getNumberOfTrips()
            numberOfTripsList = numberOfTrips.map { it.value }
            _numberOfTrips.postValue(0)
        }
    }

    fun getNumberOfTripsForMetro() {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfTrips = numberOfTripsRepository.getNumberOfTrips()
            numberOfTripsList = numberOfTrips.map { it.value }.filterIndexed { index, _ ->
                index != 1 && index != 2 && index != 3 && index != 6 && index != 11
            }
            _numberOfTripsForMetro.postValue(0)
        }
    }

    fun getPrice(body: BodyForGetPriceByNumberOfTrips, body2: BodyForGetPriceByNumberOfTrips) {
        viewModelScope.launch(Dispatchers.IO) {
            val result1 = getPriceForItem(body)
            val result2 = getPriceForItem(body2)
            val sum = result1 + result2
            _price.postValue(sum)
        }
    }

    private suspend fun getPriceForItem(body: BodyForGetPriceByNumberOfTrips): Double {
        val price: Price = numberOfTripsRepository.getPrice(body)
        return price.price
    }


}