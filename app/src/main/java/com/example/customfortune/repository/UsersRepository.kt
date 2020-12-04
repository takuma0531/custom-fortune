package com.example.customfortune.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.customfortune.database.user.User
import com.example.customfortune.database.user.UserDao
import kotlinx.coroutines.flow.Flow

class UsersRepository(private val userDao: UserDao) {
    @WorkerThread
    fun get(key: Long): LiveData<User> {
        return userDao.get(key)
    }

    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    @WorkerThread
    suspend fun update(user: User) {
        userDao.update(user)
    }
}