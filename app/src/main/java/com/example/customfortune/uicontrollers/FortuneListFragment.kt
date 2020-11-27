package com.example.customfortune.uicontrollers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.customfortune.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customfortune.MainActivity
import com.example.customfortune.databinding.FragmentFortuneListBinding
import com.example.customfortune.uicontrollers.adapters.CardListAdapter
import com.example.customfortune.utils.DependencyService
import com.example.customfortune.viewmodels.CardViewModel

class FortuneListFragment : Fragment() {
    private lateinit var binding: FragmentFortuneListBinding
    private var adapter: CardListAdapter? = null
    private lateinit var viewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fortune_list, container, false)

        setupViewModel()

        val recyclerView = binding.fortuneList
        adapter = CardListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cards.observe(viewLifecycleOwner) {
            it.let { adapter?.submitList(it) }
        }
    }

    private fun setupViewModel() {
        viewModel = DependencyService.serveCardViewModel((activity as MainActivity?)!!)
    }
}