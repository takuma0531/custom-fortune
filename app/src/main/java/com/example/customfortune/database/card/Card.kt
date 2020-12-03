package com.example.customfortune.database.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class Card(var image: String, var description: String) {
    @PrimaryKey(autoGenerate = true)
    var cardId: Long? = null
}