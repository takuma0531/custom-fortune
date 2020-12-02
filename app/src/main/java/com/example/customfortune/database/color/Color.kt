package com.example.customfortune.database.color

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.customfortune.R

@Entity(tableName = "color_table")
data class Color(val color: Int) {
    @PrimaryKey(autoGenerate = true)
    val colorId: Long? = null
}