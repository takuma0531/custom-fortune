package com.example.customfortune.uicontrollers

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    private val REQUEST_CODE_CAPTURE: Int = 100
    private val REQUEST_CODE_GALLERY: Int = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_fortune, container, false)

        setupViewModel()
        clickUpdateButton()
        clickRemoveButton()
        clickTakePhotoButton()
        clickChooseButton()

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
                if (card.image.isNotEmpty()) {
                    val bitmap = TypeConverter.getBitmapFromString(entity.image)
                    binding.imageFortune.setImageBitmap(bitmap)
                }
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

    private fun clickTakePhotoButton() {
        binding.buttonTakingPhoto.setOnClickListener {
            if (checkPermission()) {
                takePhoto()
            } else requestPermission(REQUEST_CODE_CAPTURE)
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAPTURE)
    }

    private fun clickChooseButton() {
        binding.buttonChoosingPhoto.setOnClickListener {
            if (checkPermission()) {
                choosePhoto()
            } else requestPermission(REQUEST_CODE_GALLERY)
        }
    }

    private fun choosePhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }

    private fun requestPermission(requestCode: Int) {
        ActivityCompat.requestPermissions((activity as MainActivity?)!!, arrayOf(READ_EXTERNAL_STORAGE, CAMERA), requestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_CAPTURE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto()
                } else {
                    Toast.makeText((activity as MainActivity?)!!, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_CODE_GALLERY -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    choosePhoto()
                } else {
                    Toast.makeText((activity as MainActivity?)!!, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Toast.makeText((activity as MainActivity?)!!, "Invalid request", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission((activity as MainActivity?)!!, CAMERA)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission((activity as MainActivity?)!!,
                READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    // called after startActivityForResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val bitmap = data.extras?.get("data") as Bitmap
            binding.imageFortune.setImageBitmap(bitmap)
        }
    }
}