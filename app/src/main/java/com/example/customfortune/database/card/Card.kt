package com.example.customfortune.database.card

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "card_table")
data class Card(var image: String, var description: String): Serializable {
    @PrimaryKey(autoGenerate = true)
    var cardId: Long? = 0
}