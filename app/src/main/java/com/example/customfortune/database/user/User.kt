package com.example.customfortune.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(val name: String) {
    @PrimaryKey(autoGenerate = true)
    var userId: Long? = null

    @ColumnInfo(name = "nickname")
    var nickname: String = name
}