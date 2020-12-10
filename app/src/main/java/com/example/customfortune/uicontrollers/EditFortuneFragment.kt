package com.example.customfortune.uicontrollers

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.customfortune.MainActivity
import com.example.customfortune.R
import com.example.customfortune.database.card.Card
import com.example.customfortune.databinding.FragmentEditFortuneBinding
import com.example.customfortune.utils.DependencyService
import com.example.customfortune.utils.TypeConverter
import com.example.customfortune.viewmodels.CardViewModel
import com.google.android.material.snackbar.Snackbar

class EditFortuneFragment : Fragment() {
    private lateinit var binding: FragmentEditFortuneBinding
    private lateinit var viewModel: CardViewModel
    private lateinit var card: Card

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_fortune, container, false)

        setupViewModel()
        clickUpdateButton()
        clickRemoveButton()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { arguments ->
            EditFortuneFragmentArgs.fromBundle(arguments)
        }

        args.let { it ->
            viewModel.get(it?.cardId!!).observe(viewLifecycleOwner) { entity ->
                card = entity
                val bitmap = TypeConverter.getBitmapFromString(entity.image)
                binding.imageFortune.setImageBitmap(bitmap)
                binding.textEditDescriptionInput.setText(entity.description)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = DependencyService.serveCardViewModel((activity as MainActivity?)!!)
    }

    private fun clickUpdateButton() {
        binding.buttonUpdateCard.setOnClickListener {
            val bitmap = (binding.imageFortune.drawable as BitmapDrawable).bitmap
            card.image = TypeConverter.getStringFromBitmap(bitmap)
            val description = binding.textEditDescriptionInput.editableText.toString()
            card.description = description

            viewModel.update(card)

            Snackbar.make(requireView(), "Successfully updated!!", Snackbar.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_editFortuneFragment_to_fortuneListFragment)
        }
    }

    private fun clickRemoveButton() {
        binding.buttonRemoveCard.setOnClickListener {
            viewModel.cards.observe(viewLifecycleOwner) { cards ->
                if (cards.size < 5) {
                    Snackbar.make(requireView(), "Cannot be removed when the number of fortunes is less than five.", Snackbar.LENGTH_LONG).show()
                } else {
                    viewModel.delete(card)

                    Snackbar.make(requireView(), "Successfully removed!!.", Snackbar.LENGTH_LONG).show()

                    findNavController().navigate(R.id.action_editFortuneFragment_to_fortuneListFragment)
                }
            }
        }
    }
}