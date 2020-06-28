package com.spydevs.fiestonvirtual.ui.main.gallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.ui.gallerydetail.GalleryDetailActivity
import com.spydevs.fiestonvirtual.ui.main.gallery.adapter.GalleryItemAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.koin.android.ext.android.inject

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val galleryViewModel: GalleryViewModel by inject()
    private val galleryItemAdapter: GalleryItemAdapter by lazy {
        GalleryItemAdapter { GalleryItem ->
            startActivity(
                Intent(context, GalleryDetailActivity::class.java).apply {
                    putExtra(GalleryDetailActivity.OBJECT_GALLERY_ITEM, GalleryItem)
                }
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpGalleryList()
        subscribeToPhotoList()
        galleryViewModel.getPhotoList(GalleryRequest(1, 1))
    }

    private fun setUpGalleryList() {
        galleryFragment_rv.apply {
            adapter = this@GalleryFragment.galleryItemAdapter
        }
    }

    private fun subscribeToPhotoList() {
        this.galleryViewModel.galleryItemList.observe(viewLifecycleOwner, Observer {
            this@GalleryFragment.galleryItemAdapter.addData(it)
        })
    }

}
