package com.example.customfortune.utils

import com.example.customfortune.database.card.Card
import kotlin.random.Random

object FortuneItemService {
    fun getRandomCard(cards: List<Card>): Card {
        val int = Random.nextInt(cards.size)

        return cards.elementAt(int)
    }
}