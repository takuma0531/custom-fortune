package com.example.customfortune.uicontrollers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.customfortune.R
import com.example.customfortune.database.card.Card

class CardListAdapter : ListAdapter<Card, CardListAdapter.CardViewHolder>(CardComparator) {
    private var itemClickListener: ((Card) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_fortune_card, parent, false)

        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.cardItemDescription)
        private val imageView: ImageView = itemView.findViewById(R.id.cardItemImage)

        fun bind(card: Card) {
            textView.text = card.description

            itemView.setOnClickListener {
                itemClickListener?.let { listener ->
                    listener(card)
                }
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

    // be used in FortuneListFragment
    fun setItemClickListener(listener: (Card) -> Unit) {
        itemClickListener = listener
    }
}