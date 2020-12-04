package com.example.customfortune.database.user

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from user_table WHERE userId = :key")
    fun get(key: Long): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(user: User)
}