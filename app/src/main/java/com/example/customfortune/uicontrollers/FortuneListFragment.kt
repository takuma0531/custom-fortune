package com.example.customfortune.uicontrollers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.customfortune.R
import com.example.customfortune.databinding.FragmentFortuneListBinding

class FortuneListFragment : Fragment() {
    private lateinit var binding: FragmentFortuneListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fortune_list, container, false)

        return binding.root
    }
}