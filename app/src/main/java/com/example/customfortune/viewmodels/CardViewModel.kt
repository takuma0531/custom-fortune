package com.example.customfortune.viewmodels

import androidx.lifecycle.*
import com.example.customfortune.database.card.Card
import com.example.customfortune.repository.CardsRepository
import kotlinx.coroutines.launch

class CardViewModel(private val repository: CardsRepository): ViewModel() {
    private val _cards: MutableLiveData<List<Card>> = MutableLiveData()
    val cards: LiveData<List<Card>>
        get() = _cards

    init {
        _cards.value = listOf(
            Card("Image1", "desc1"),
            Card("Image2", "desc2")
        )
//        repository.cards.asLiveData()
    }

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