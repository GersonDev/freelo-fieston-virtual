package com.spydevs.fiestonvirtual.ui.main.photo

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : Fragment(R.layout.fragment_photo) {

    private lateinit var photoViewModel: PhotoViewModel

    private var permissionsList = mutableListOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        subscribeToCategories()
//        subscribeToUser()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setupViews()
        setupViewListeners()

        //homeViewModel.getUsers()
    }

    private fun setupViewListeners() {
        photoButton.setOnClickListener {

            (requireActivity() as MainActivity).validatePermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            //validatePermission(permissionsList[CameraActivity.INDEX_READ_PERMISSION])
        }
    }

    fun setImage(uri: Uri?) {
        photoImageView.setImageURI(uri)
    }



    companion object {
        const val INDEX_READ_PERMISSION = 1
    }

}
