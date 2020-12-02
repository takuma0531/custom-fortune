package com.example.customfortune.viewmodels

import androidx.lifecycle.*
import com.example.customfortune.database.card.Card
import com.example.customfortune.repository.CardsRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class CardViewModel(private val repository: CardsRepository): ViewModel() {
    val cards: LiveData<List<Card>> = repository.cards.asLiveData()

    fun get(key: Long): LiveData<Card> =  repository.get(key).asLiveData()

    fun insert(card: Card) = viewModelScope.launch {
        repository.insert(card)
    }

    fun update(card: Card) = viewModelScope.launch {
        repository.update(card)
    }

    fun delete(card: Card) = viewModelScope.launch {
        repository.delete(card)
    }
}