package com.example.customfortune.database.color

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.customfortune.R

@Entity(tableName = "color_table")
data class Color(
    @PrimaryKey(autoGenerate = true)
    var colorId: Long = 0L,

    @ColumnInfo(name = "color")
    val color: Int = R.color.purple_500
)