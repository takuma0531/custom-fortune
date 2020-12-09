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
import com.example.customfortune.viewmodels.ColorViewModel
import com.example.customfortune.viewmodels.UserViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var cardViewModel: CardViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        setupViewModels()
        representRandomCard()

        return binding.root
    }

    private fun setupViewModels() {
        cardViewModel = DependencyService.serveCardViewModel((activity as MainActivity?)!!)
        userViewModel = DependencyService.serveUserViewModel((activity as MainActivity?)!!)
    }

    private fun representRandomCard() {
        cardViewModel.cards.observe(viewLifecycleOwner) { cards ->
            cards.let {
                val card = FortuneItemService.getRandomCard(cards)

                binding.textResultDescription.text = card.description

            }
        }

        userViewModel.get(0).observe(viewLifecycleOwner) { user ->
            binding.textNickname.text = user.nickname
        }
    }
}