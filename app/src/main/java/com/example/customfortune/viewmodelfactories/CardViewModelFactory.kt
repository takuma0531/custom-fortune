package com.example.customfortune.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customfortune.repository.CardsRepository
import com.example.customfortune.viewmodels.CardViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class CardViewModelFactory(private val repository: CardsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
            return CardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}