package com.example.customfortune.repository

import androidx.annotation.WorkerThread
import com.example.customfortune.database.color.Color
import com.example.customfortune.database.color.ColorDao
import kotlinx.coroutines.flow.Flow

class ColorsRepository(private val colorDao: ColorDao) {
    fun get(key: Long): Flow<Color>  {
        return colorDao.get(key)
    }

    @WorkerThread
    suspend fun update(color: Color) {
        colorDao.update(color)
    }
}