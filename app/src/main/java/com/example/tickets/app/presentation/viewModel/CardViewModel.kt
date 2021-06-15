package com.example.tickets.app.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.app.data.model.Card
import com.example.tickets.app.data.model.Transactions
import com.example.tickets.app.domain.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewModel constructor(private val cardRepository: CardRepository) : ViewModel() {
    private val _card = MutableLiveData<List<Card>>()
    val card: LiveData<List<Card>> = _card

    private val _transactionsByCard = MutableLiveData<List<Transactions>>()
    val transactionsByCard: LiveData<List<Transactions>> = _transactionsByCard

    var cardList: List<Card> = listOf()

    fun getCard(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            cardList = cardRepository.getCard(number)
            _card.postValue(cardList)
        }
    }

    fun getTransactionByCard(cardId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val transaction = cardRepository.getTransactionByCard(cardId)
            _transactionsByCard.postValue(transaction)
        }
    }

}