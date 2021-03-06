package com.example.customfortune.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.customfortune.database.card.Card
import com.example.customfortune.database.card.CardDao
import kotlinx.coroutines.flow.Flow

class CardsRepository(private val cardDao: CardDao) {
    val cards: Flow<List<Card>> = cardDao.getAll()

    @WorkerThread
    fun get(key: Long): LiveData<Card> {
        return cardDao.get(key)
    }

    @WorkerThread
    suspend fun insert(card: Card) {
        cardDao.insert(card)
    }

    @WorkerThread
    suspend fun update(card: Card) {
        cardDao.update(card)
    }

    @WorkerThread
    suspend fun delete(card: Card) {
        cardDao.delete(card)
    }
}