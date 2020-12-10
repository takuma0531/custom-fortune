package com.example.customfortune.uicontrollers

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.example.customfortune.MainActivity
import com.example.customfortune.R
import com.example.customfortune.databinding.FragmentTitleBinding
import com.example.customfortune.utils.DependencyService
import com.example.customfortune.viewmodels.ColorViewModel

class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding
    private lateinit var colorViewModel: ColorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container,false)

        binding.buttonStart.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_resultFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupColorViewModel()
//        changeTitleColor()
    }

    private fun setupColorViewModel() {
        colorViewModel = DependencyService.serveColorViewModel((activity as MainActivity?)!!)
    }

    private fun changeTitleColor() {
        colorViewModel.get(0).let {
            it.observe(viewLifecycleOwner) { color ->
                val colorInt = when (color.color) {
                    0 -> Color.parseColor("#FF6200EE")
                    1 -> Color.parseColor("#FF03DAC5")
                    2 -> Color.parseColor("#FF000000")
                    3 -> Color.parseColor("#FFFFFFFF")
                    else -> Color.parseColor("#FF6200EE")
                }

                binding.textTitle.setTextColor(colorInt)
            }
        }
    }
}