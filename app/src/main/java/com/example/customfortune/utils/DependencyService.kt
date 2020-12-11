package com.example.customfortune.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.customfortune.database.FortuneDatabase
import com.example.customfortune.repository.CardsRepository
import com.example.customfortune.repository.ColorsRepository
import com.example.customfortune.repository.UsersRepository
import com.example.customfortune.viewmodelfactories.CardViewModelFactory
import com.example.customfortune.viewmodelfactories.ColorViewModelFactory
import com.example.customfortune.viewmodelfactories.UserViewModelFactory
import com.example.customfortune.viewmodels.CardViewModel
import com.example.customfortune.viewmodels.ColorViewModel
import com.example.customfortune.viewmodels.UserViewModel

object DependencyService {
    fun serveCardViewModel(database: FortuneDatabase, activity: AppCompatActivity): CardViewModel {
        val repository = CardsRepository(database.cardDao())

        return ViewModelProvider(
            activity, CardViewModelFactory(repository)
        )
            .get(CardViewModel::class.java)
    }

    fun serveColorViewModel(database: FortuneDatabase, activity: AppCompatActivity): ColorViewModel {
        val repository = ColorsRepository(database.colorDao())

        return ViewModelProvider(
                activity, ColorViewModelFactory(repository)
        )
                .get(ColorViewModel::class.java)
    }

    fun serveUserViewModel(database: FortuneDatabase, activity: AppCompatActivity): UserViewModel {
        val repository = UsersRepository(database.userDao())

        return ViewModelProvider(
                activity, UserViewModelFactory(repository)
        )
                .get(UserViewModel::class.java)
    }
}