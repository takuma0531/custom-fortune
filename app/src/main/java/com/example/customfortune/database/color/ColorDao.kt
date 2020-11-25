package com.example.customfortune.database.color

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Query("SELECT * from color_table WHERE colorId = :key")
    fun get(key: Long): Flow<Color>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(color: Color)
}