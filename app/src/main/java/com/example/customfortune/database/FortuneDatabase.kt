package com.example.customfortune.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.customfortune.database.card.Card
import com.example.customfortune.database.card.CardDao
import com.example.customfortune.database.color.Color
import com.example.customfortune.database.color.ColorDao
import com.example.customfortune.database.user.User
import com.example.customfortune.database.user.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Card::class, Color::class, User::class], version = 1, exportSchema = false)
public abstract class FortuneDatabase: RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun colorDao(): ColorDao
    abstract fun userDao(): UserDao

    private class FortuneDatabaseCallBack(private val scope : CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val cardDao = database.cardDao()

                    var card = Card("sample_image", "Excellent day")
                }
            }
        }
    }

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