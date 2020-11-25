package com.example.customfortune.database.card

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * from card_table WHERE cardId = :key")
    suspend fun get(key: Long): Card?

    @Query("SELECT * from card_table")
    suspend fun getAll(): Flow<List<Card>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(card: Card)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(card: Card)

    @Delete
    suspend fun delete(card: Card)
}