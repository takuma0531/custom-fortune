package com.example.customfortune.database.card

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class Card(val img: String, val desc: String) {
    @PrimaryKey(autoGenerate = true)
    var cardId: Long? = null

    @ColumnInfo(name = "image")
    val image: String = img

    @ColumnInfo(name = "description")
    val description: String = desc
}