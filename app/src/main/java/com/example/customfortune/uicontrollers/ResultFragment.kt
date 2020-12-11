package com.example.customfortune.uicontrollers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.customfortune.MainActivity
import com.example.customfortune.R
import com.example.customfortune.databinding.FragmentResultBinding
import com.example.customfortune.utils.FortuneItemService
import com.example.customfortune.utils.TypeConverter
import com.example.customfortune.viewmodels.CardViewModel
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

        cardViewModel = (activity as MainActivity?)!!.cardViewModel
        userViewModel = (activity as MainActivity?)!!.userViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        representRandomCard()
        clickRestart()
    }

    private fun representRandomCard() {
        cardViewModel.cards?.let {
            it.observe(viewLifecycleOwner) { cards ->
                cards.let {
                    val card = FortuneItemService.getRandomCard(cards)

                    binding.textResultDescription.text = card.description
                    val bitmap = TypeConverter.getBitmapFromString(card.image)
                    binding.imageResult.setImageBitmap(bitmap)
                }
            }
        }

        userViewModel.get(0)?.let {
            it.observe(viewLifecycleOwner) { user ->
                user.let {
                    binding.textResultTitle.text = user.nickname + "'s " + "Fortune"
                }
            }
        }
    }

    private fun clickRestart() {
        binding.buttonReturn.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_self)
        }
    }
}