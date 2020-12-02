package com.example.customfortune.database.card

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class Card(val image: String, val description: String) {
    @PrimaryKey(autoGenerate = true)
    var cardId: Long? = null
}