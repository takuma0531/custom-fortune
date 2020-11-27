package com.example.customfortune.uicontrollers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.customfortune.R
import com.example.customfortune.database.card.Card

class CardListAdapter : ListAdapter<Card, CardListAdapter.CardViewHolder>(CardComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.description)
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardItemTextView: TextView = itemView.findViewById(R.id.cardItemDescription)

        fun bind(text: String?) {
            cardItemTextView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): CardViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_fortune_card, parent, false)

                return CardViewHolder(view)
            }
        }
    }

    companion object {
        private val CardComparator = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                return oldItem.cardId == newItem.cardId
            }
        }
    }
}