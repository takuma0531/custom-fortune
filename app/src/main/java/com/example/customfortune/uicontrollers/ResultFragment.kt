package com.example.customfortune.uicontrollers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.customfortune.MainActivity
import com.example.customfortune.R
import com.example.customfortune.databinding.FragmentResultBinding
import com.example.customfortune.utils.DependencyService
import com.example.customfortune.utils.FortuneItemService
import com.example.customfortune.viewmodels.CardViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        viewModel = DependencyService.serveCardViewModel((activity as MainActivity?)!!)
        representRandomCard()

        return binding.root
    }

    private fun representRandomCard() {
            viewModel.cards.observe(viewLifecycleOwner) { cards ->
                cards.let {
                    val card = FortuneItemService.getRandomCard(cards)

                    binding.textResultDescription.text = card.description
//                    binding.imageResult
                }
        }
    }
}