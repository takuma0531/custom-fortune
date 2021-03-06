package com.example.customfortune.uicontrollers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.customfortune.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customfortune.MainActivity
import com.example.customfortune.databinding.FragmentFortuneListBinding
import com.example.customfortune.uicontrollers.adapters.CardListAdapter
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

        viewModel = (activity as MainActivity?)!!.cardViewModel
        setupRecyclerView()
        setupClickListenerOnEachItem()
        clickCreateButton()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cards?.let {
              it.observe(viewLifecycleOwner) { cards ->
                  cards.let { adapter?.submitList(cards) }
              }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.fortuneList
        adapter = CardListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupClickListenerOnEachItem() {
        adapter?.setItemClickListener { card ->
            val action
                    = FortuneListFragmentDirections.actionFortuneListFragmentToEditFortuneFragment(card.cardId!!)
            findNavController().navigate(action)
        }
    }

    private fun clickCreateButton() {
        binding.buttonStartCreatingFortune.setOnClickListener {
            findNavController().navigate(R.id.action_fortuneListFragment_to_createFortuneFragment)
        }
    }
}