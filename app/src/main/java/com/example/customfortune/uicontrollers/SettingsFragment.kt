package com.example.customfortune.uicontrollers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.customfortune.MainActivity
import com.example.customfortune.R
import com.example.customfortune.database.color.Color
import com.example.customfortune.database.user.User
import com.example.customfortune.databinding.FragmentSettingsBinding
import com.example.customfortune.viewmodels.ColorViewModel
import com.example.customfortune.viewmodels.UserViewModel
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var colorViewModel: ColorViewModel
    private lateinit var user: User
    private lateinit var color: Color

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        userViewModel = (activity as MainActivity?)!!.userViewModel
        colorViewModel = (activity as MainActivity?)!!.colorViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setName()
        setColorSpinner()
        clickUpdateName()
    }

    private fun setName() {
        userViewModel.get(0)?.let {
            it.observe(viewLifecycleOwner) { entity ->
                  entity.let {
                      user = entity
                      binding.textEditNameInput.setText(user.nickname)
                  }
            }
        }
    }

    private fun setColorSpinner() {
        val spinner = binding.spinnerFrameColor

        ArrayAdapter.createFromResource(
                (activity as MainActivity?)!!,
                R.array.array_color,
                R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        colorViewModel.get(0)?.let {
             it.observe(viewLifecycleOwner) { entity ->
                 color = entity

                 spinner.onItemSelectedListener = this
                 spinner.setSelection(color.color)
             }
        }
    }

    private fun clickUpdateName() {
        binding.buttonUpdateName.setOnClickListener {
            val name = binding.textEditNameInput.editableText.toString()
            if (name.isEmpty()) {
                Snackbar.make(requireView(), "Name cannot be empty.", Snackbar.LENGTH_LONG).show()
            } else {
                user.nickname = name
                userViewModel.update(user)

                Snackbar.make(requireView(), "Successfully updated!.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        color.color = pos
        colorViewModel.update(color)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}