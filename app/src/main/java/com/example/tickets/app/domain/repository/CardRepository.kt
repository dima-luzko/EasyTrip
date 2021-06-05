package com.example.tickets.app.domain.repository

import com.example.tickets.app.data.model.Card
import com.example.tickets.app.data.model.Transactions

interface CardRepository {
    suspend fun getCard(cardNumber: String): List<Card>
    suspend fun getTransactionByCard(cardId: Int): List<Transactions>
}