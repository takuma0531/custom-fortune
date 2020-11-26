package com.example.customfortune.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.customfortune.database.card.Card
import com.example.customfortune.database.card.CardDao
import com.example.customfortune.database.color.Color
import com.example.customfortune.database.color.ColorDao
import com.example.customfortune.database.user.User
import com.example.customfortune.database.user.UserDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Card::class, Color::class, User::class], version = 1, exportSchema = false)
public abstract class FortuneDatabase: RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun colorDao(): ColorDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: FortuneDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): FortuneDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FortuneDatabase::class.java,
                    "fortune_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}