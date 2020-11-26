package com.example.customfortune.uicontrollers.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customfortune.R

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var cardItemView: TextView

    fun bind(text: String?) {
        cardItemView.text = text
    }

    companion object {
        fun create(parent: ViewGroup): CardViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_fortune_list, parent, false)

            return CardViewHolder(view)
        }
    }
}