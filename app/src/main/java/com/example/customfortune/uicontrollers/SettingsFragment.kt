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
import com.example.customfortune.database.user.User
import com.example.customfortune.databinding.FragmentSettingsBinding
import com.example.customfortune.utils.DependencyService
import com.example.customfortune.viewmodels.ColorViewModel
import com.example.customfortune.viewmodels.UserViewModel
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var colorViewModel: ColorViewModel
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        setupViewModels()
        setName()
        clickUpdateName()

        return binding.root
    }

    private fun setupViewModels() {
        userViewModel = DependencyService.serveUserViewModel((activity as MainActivity?)!!)
        colorViewModel = DependencyService.serveColorViewModel((activity as MainActivity?)!!)
    }

    private fun setName() {
        userViewModel.get(1).observe(viewLifecycleOwner) { entity ->
            user = entity
        }
    }

    private fun clickUpdateName() {
        val name = binding.inputUserName.editableText.toString()
        if (name.isEmpty()) {
            Snackbar.make(requireView(), "Name cannot be empty.", Snackbar.LENGTH_LONG).show()
        } else {
            user.nickname = name
            userViewModel.update(user)
        }
    }

    private fun setColors() {

    }

    private fun changeColorSpinner() {

    }
}