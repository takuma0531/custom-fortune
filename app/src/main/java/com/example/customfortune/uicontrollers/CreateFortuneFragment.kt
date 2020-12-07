package com.example.customfortune.uicontrollers

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    private val REQUEST_CODE_CAMTURE: Int = 100
    private val REQUEST_CODE_GALLERY: Int = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_fortune, container, false)

        setupViewModel()
        clickCreateButton()
        clickTakePhotoButton()
        clickChooseButton()

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

    private fun clickTakePhotoButton() {
        binding.buttonTakingPhoto.setOnClickListener {
            if (checkPermission()) {
                takePhoto()
            } else requestPermission(REQUEST_CODE_CAMTURE)
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMTURE)
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
            REQUEST_CODE_CAMTURE -> {
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

        val bitmap = data?.extras?.get("data") as Bitmap
        // TODO: interact with viewModel
    }
}