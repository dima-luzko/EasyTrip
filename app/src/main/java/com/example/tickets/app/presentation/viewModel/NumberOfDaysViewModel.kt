package com.example.tickets.app.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.app.data.model.BodyForGetPriceByNumberOfDays
import com.example.tickets.app.data.model.NumberOfDaysOrTrips
import com.example.tickets.app.data.model.Price
import com.example.tickets.app.domain.repository.NumberOfDaysRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumberOfDaysViewModel constructor(private val numberOfDaysRepository: NumberOfDaysRepository) :
    ViewModel() {

    private val _numberOfDays = MutableLiveData<List<NumberOfDaysOrTrips>>()
    val numberOfDays: LiveData<List<NumberOfDaysOrTrips>> = _numberOfDays

    private val _price = MutableLiveData<Price>()
    val price: LiveData<Price> = _price

    fun getNumberOfDays() {
        viewModelScope.launch(Dispatchers.IO) {
            val numberOfDays = numberOfDaysRepository.getNumberOfDays()
            _numberOfDays.postValue(numberOfDays)
        }
    }

    fun getPrice(body: BodyForGetPriceByNumberOfDays){
        viewModelScope.launch(Dispatchers.IO) {
            val price = numberOfDaysRepository.getPrice(body)
            _price.postValue(price)
        }
    }
}