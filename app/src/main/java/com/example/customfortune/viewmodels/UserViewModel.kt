package com.example.customfortune.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.customfortune.database.user.User
import com.example.customfortune.repository.UsersRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UsersRepository): ViewModel() {
    fun get(key: Long): LiveData<User> =  repository.get(key).asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
    }
}