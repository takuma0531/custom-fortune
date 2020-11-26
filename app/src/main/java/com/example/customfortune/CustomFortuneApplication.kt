package com.example.customfortune

import android.app.Application
import com.example.customfortune.database.FortuneDatabase
import com.example.customfortune.repository.CardsRepository
import com.example.customfortune.repository.ColorsRepository
import com.example.customfortune.repository.UsersRepository

class CustomFortuneApplication : Application() {
    val database by lazy { FortuneDatabase.getDatabase(this) }
    val repository by lazy {
        CardsRepository(database.cardDao())
        ColorsRepository(database.colorDao())
        UsersRepository(database.userDao())
    }
}