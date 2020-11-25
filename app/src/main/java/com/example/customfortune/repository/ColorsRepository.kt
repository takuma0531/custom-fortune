package com.example.customfortune.repository

import androidx.annotation.WorkerThread
import com.example.customfortune.database.color.Color
import com.example.customfortune.database.color.ColorDao

class ColorsRepository(private val colorDao: ColorDao) {
    @WorkerThread
    suspend fun get(key: Long): Color? {
        return colorDao.get(key)
    }

    @WorkerThread
    suspend fun update(color: Color) {
        colorDao.update(color)
    }
}