package com.example.customfortune.repository

import androidx.annotation.WorkerThread
import com.example.customfortune.database.user.User
import com.example.customfortune.database.user.UserDao

class UsersRepository(private val userDao: UserDao) {
    @WorkerThread
    suspend fun get(key: Long): User? {
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