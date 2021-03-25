package com.alfatih.shoping.trader.add_product

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alfatih.shoping.databinding.AddProductFragmentBinding

class AddProductFragment : Fragment() {

    companion object {
        fun newInstance() = AddProductFragment()
    }

    private lateinit var viewModel: AddProductViewModel
    private lateinit var _binding: AddProductFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = AddProductFragmentBinding.inflate(inflater, container, false)

        _binding.imageViewUpload.setOnClickListener {
            dispatchTakePictureIntent()
        }


        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)
        // TODO: Use the ViewModel
    }

    val REQUEST_IMAGE_CAPTURE = 1
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            _binding.imageViewUpload.setImageBitmap(imageBitmap)
        }
    }

}