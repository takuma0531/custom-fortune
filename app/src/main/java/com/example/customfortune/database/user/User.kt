package com.example.customfortune.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(var nickname: String) {
    @PrimaryKey(autoGenerate = true)
    var userId: Long? = 0
}