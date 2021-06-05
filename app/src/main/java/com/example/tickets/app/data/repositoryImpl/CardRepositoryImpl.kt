package com.example.tickets.app.data.repositoryImpl

import com.example.tickets.app.data.model.Card
import com.example.tickets.app.data.model.Transactions
import com.example.tickets.app.domain.repository.CardRepository
import com.example.tickets.framework.remote.RemoteDataSource

class CardRepositoryImpl(private val dataSource: RemoteDataSource) : CardRepository {
    override suspend fun getCard(cardNumber: String): List<Card> {
        return dataSource.instance.getCardByCardNumber(cardNumber = cardNumber)
    }

    override suspend fun getTransactionByCard(cardId: Int): List<Transactions> {
        return dataSource.instance.getTransactionsByCardNumber(cardId = cardId)
    }
}