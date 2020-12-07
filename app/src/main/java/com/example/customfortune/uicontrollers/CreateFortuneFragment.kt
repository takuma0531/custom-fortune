package com.example.customfortune.uicontrollers

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.customfortune.MainActivity
import com.example.customfortune.R
import com.example.customfortune.database.card.Card
import com.example.customfortune.databinding.FragmentCreateFortuneBinding
import com.example.customfortune.utils.DependencyService
import com.example.customfortune.viewmodels.CardViewModel

class CreateFortuneFragment : Fragment() {
    private lateinit var binding: FragmentCreateFortuneBinding
    private lateinit var viewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_fortune, container, false)

        setupViewModel()
        clickCreateButton()


        return binding.root
    }

    private fun setupViewModel() {
        viewModel = DependencyService.serveCardViewModel((activity as MainActivity?)!!)
    }

    private fun clickCreateButton() {
        // TODO:
        val image = "sample image"
        val description = binding.textFortuneDescriptionTitle.editableText.toString()
        val card = Card(description, image)

        viewModel.insert(card)
    }
}