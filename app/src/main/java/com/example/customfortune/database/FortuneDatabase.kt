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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

@Database(entities = [Card::class, Color::class, User::class], version = 1)
public abstract class FortuneDatabase : RoomDatabase() {
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
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(FortuneDatabaseCallback(scope))
                        .build()
                INSTANCE = instance

                instance
            }
        }

        private class FortuneDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let {
                    scope.launch(Dispatchers.IO) {
                        populateCards(it.cardDao())
                        populateColor(it.colorDao())
                        populateUser(it.userDao())
                    }
                }
            }
        }

        suspend fun populateCards(cardDao: CardDao) {
            val card1 = Card("", "Excellent day")
            val card2 = Card("", "Nice day")
            val card3 = Card("", "Wonderful day")
            val card4 = Card("", "Good day")
            val card5 = Card("", "Bad day")
            val card6 = Card("", "A day")
            val card7 = Card("", "Normal day")

            cardDao.apply {
                insert(card1)
                insert(card2)
                insert(card3)
                insert(card4)
                insert(card5)
                insert(card6)
                insert(card7)
            }
        }

        suspend fun populateColor(colorDao: ColorDao) {
            val color = Color(0)
            colorDao.insert(color)
        }

        suspend fun populateUser(userDao: UserDao) {
            val user = User("guest")
            userDao.insert(user)
        }
    }
}
