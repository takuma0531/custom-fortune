package com.example.customfortune.database.color

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Query("SELECT * from color_table WHERE colorId = :key")
    fun get(key: Long): LiveData<Color>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(color: Color)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(color: Color)
}