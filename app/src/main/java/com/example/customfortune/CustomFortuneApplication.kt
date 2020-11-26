package com.example.customfortune

import android.app.Application
import com.example.customfortune.database.FortuneDatabase
import com.example.customfortune.repository.CardsRepository
import com.example.customfortune.repository.ColorsRepository
import com.example.customfortune.repository.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CustomFortuneApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { FortuneDatabase.getDatabase(this, applicationScope) }
    val repository by lazy {
        CardsRepository(database.cardDao())
        ColorsRepository(database.colorDao())
        UsersRepository(database.userDao())
    }
}