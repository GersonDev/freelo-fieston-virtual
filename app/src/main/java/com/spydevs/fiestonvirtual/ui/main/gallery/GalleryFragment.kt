package com.spydevs.fiestonvirtual.ui.main.gallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.gallerydetail.GalleryDetailActivity
import com.spydevs.fiestonvirtual.ui.main.gallery.adapter.GalleryItemAdapter
import com.spydevs.fiestonvirtual.util.extensions.setupAlertDialog
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import kotlinx.android.synthetic.main.fragment_gallery.*
import org.koin.android.ext.android.inject

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val galleryViewModel: GalleryViewModel by inject()
    private val galleryItemAdapter: GalleryItemAdapter by lazy {
        GalleryItemAdapter { galleryItem ->
            //TODO enable later.
            /*
            startActivity(
                Intent(context, GalleryDetailActivity::class.java).apply {
                    putExtra(GalleryDetailActivity.OBJECT_GALLERY_ITEM, galleryItem)
                }
            )
             */
        }
    }
    private val dialogProgress by lazy {
        activity?.setupLoadingAlertDialog()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpGalleryList()
        subscribeToGalleryList()
        subscribeToError()
        subscribeToLoading()
        galleryViewModel.getPhotoList()
    }

    private fun setUpGalleryList() {
        galleryFragment_rv.apply {
            adapter = this@GalleryFragment.galleryItemAdapter
        }
    }

    private fun subscribeToGalleryList() {
        this.galleryViewModel.galleryItemList.observe(
            viewLifecycleOwner,
            Observer {
                this@GalleryFragment.galleryItemAdapter.addData(
                    (it as GalleryResult.GetGallerySuccessful).galleryItemList
                )
            }
        )
    }

    private fun subscribeToError() {
        this.galleryViewModel.error.observe(
            viewLifecycleOwner,
            Observer {
                (it as GalleryResult.GetGalleryError).errorResponse.let { errorResponse ->
                    activity?.setupAlertDialog(
                        title = errorResponse.title,
                        message = errorResponse.message
                    )
                }
            }
        )
    }

    private fun subscribeToLoading() {
        this.galleryViewModel.loading.observe(
            viewLifecycleOwner,
            Observer {
                if ((it as GalleryResult.Loading).show) {
                    this.dialogProgress?.show()
                } else {
                    this.dialogProgress?.dismiss()
                }
            }
        )
    }

}