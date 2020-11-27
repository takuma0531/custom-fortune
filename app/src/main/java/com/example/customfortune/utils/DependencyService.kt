package com.example.customfortune.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.customfortune.database.FortuneDatabase
import com.example.customfortune.repository.CardsRepository
import com.example.customfortune.viewmodelfactories.CardViewModelFactory
import com.example.customfortune.viewmodels.CardViewModel
import com.example.customfortune.viewmodels.ColorViewModel
import com.example.customfortune.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

object DependencyService {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private lateinit var database: FortuneDatabase

    fun getDatabase(activity: AppCompatActivity) {
        database = FortuneDatabase.getDatabase(activity, applicationScope)
    }

    fun serveCardViewModel(activity: AppCompatActivity): CardViewModel {
        val repository = CardsRepository(database.cardDao())

        return ViewModelProvider(
            activity, CardViewModelFactory(repository)
        )
            .get(CardViewModel::class.java)
    }

    fun serveColorViewModel(activity: AppCompatActivity): ColorViewModel {
        TODO()
    }

    fun serveUserViewModel(activity: AppCompatActivity): UserViewModel {
        TODO()
    }
}