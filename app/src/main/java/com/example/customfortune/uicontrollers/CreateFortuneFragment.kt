package com.example.customfortune.uicontrollers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.customfortune.R
import com.example.customfortune.databinding.FragmentCreateFortuneBinding

class CreateFortuneFragment : Fragment() {
    private lateinit var binding: FragmentCreateFortuneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_fortune, container, false)

        return binding.root
    }
}