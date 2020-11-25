package com.example.customfortune.database.user

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * from user_table WHERE userId = :key")
    suspend fun get(key: Long): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(user: User)
}