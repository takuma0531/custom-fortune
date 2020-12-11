package com.example.customfortune.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customfortune.database.color.Color
import com.example.customfortune.repository.ColorsRepository
import kotlinx.coroutines.launch

class ColorViewModel(private val repository: ColorsRepository): ViewModel() {
    fun get(key: Long): LiveData<Color>? =  repository.get(key)

    fun update(color: Color) = viewModelScope.launch {
        repository.update(color)
    }
}