package com.example.customfortune.uicontrollers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.customfortune.R
import com.example.customfortune.databinding.FragmentEditFortuneBinding

class EditFortuneFragment : Fragment() {
    private lateinit var binding: FragmentEditFortuneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { arguments ->
            EditFortuneFragmentArgs.fromBundle(arguments)
        }

        binding.textEditDescriptionInput.setText(args?.description)
    }
}