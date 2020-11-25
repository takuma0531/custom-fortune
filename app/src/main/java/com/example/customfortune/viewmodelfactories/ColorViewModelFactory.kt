package com.example.customfortune.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customfortune.repository.ColorsRepository
import com.example.customfortune.viewmodels.ColorViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ColorViewModelFactory(private val repository: ColorsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ColorViewModel::class.java)) {
            return ColorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}