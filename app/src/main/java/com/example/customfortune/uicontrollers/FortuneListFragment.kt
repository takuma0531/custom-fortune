package com.example.customfortune.uicontrollers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.customfortune.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customfortune.databinding.FragmentFortuneListBinding
import com.example.customfortune.uicontrollers.adapters.CardListAdapter

class FortuneListFragment : Fragment() {
    private lateinit var binding: FragmentFortuneListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fortune_list, container, false)

        val recyclerView = binding.fortuneList
        val adapter = CardListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}