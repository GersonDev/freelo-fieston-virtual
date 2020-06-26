package com.spydevs.fiestonvirtual.ui.main.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.gallery.photo.PhotoAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.koin.android.ext.android.inject

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val galleryViewModel: GalleryViewModel by inject()
    private val photoAdapter: PhotoAdapter by lazy {
        PhotoAdapter {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpGalleryList()
        subscribeToPhotoList()
        galleryViewModel.getPhotoList()
    }

    private fun setUpGalleryList() {
        galleryFragment_rv.apply {
            adapter = this@GalleryFragment.photoAdapter
        }
    }

    private fun subscribeToPhotoList() {
        this.galleryViewModel.photoList.observe(viewLifecycleOwner, Observer {
            this@GalleryFragment.photoAdapter.addData(it.toMutableList())
        })
    }

}
