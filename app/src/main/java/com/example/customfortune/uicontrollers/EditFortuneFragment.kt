package com.example.customfortune.uicontrollers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.customfortune.MainActivity
import com.example.customfortune.R
import com.example.customfortune.database.card.Card
import com.example.customfortune.databinding.FragmentEditFortuneBinding
import com.example.customfortune.utils.DependencyService
import com.example.customfortune.viewmodels.CardViewModel

class EditFortuneFragment : Fragment() {
    private lateinit var binding: FragmentEditFortuneBinding
    private lateinit var viewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_fortune, container, false)

        setupViewModel()
        clickUpdateButton()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { arguments ->
            EditFortuneFragmentArgs.fromBundle(arguments)
        }

        binding.textEditDescriptionInput.setText(args?.description)
    }

    private fun setupViewModel() {
        viewModel = DependencyService.serveCardViewModel((activity as MainActivity?)!!)
    }

    private fun clickUpdateButton() {
        binding.buttonUpdateCard.setOnClickListener {
            val description = binding.textEditDescriptionInput.editableText.toString()
            // TODO: image
            val image = "sample img"
            viewModel.update(Card(desc = description, img =  image))

            findNavController().navigate(R.id.action_editFortuneFragment_to_fortuneListFragment)
        }
    }
}