package com.example.customfortune.database.color

import androidx.room.*

@Dao
interface ColorDao {
    @Query("SELECT * from color_table WHERE colorId = :key")
    suspend fun get(key: Long): Color?

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(color: Color)
}