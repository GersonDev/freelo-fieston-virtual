package com.spydevs.fiestonvirtual.ui.gallerydetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.ui.gallerydetail.adapter.CommentAdapter
import kotlinx.android.synthetic.main.activity_camera.toolbar
import kotlinx.android.synthetic.main.content_gallery_detail.*
import org.koin.android.ext.android.inject

class GalleryDetailActivity : AppCompatActivity() {

    private val viewModel: GalleryDetailViewModel by inject()
    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)
        setUpToolbar()
        setUpCommentList()
        subscribeToGetCommentList()
        getCommentList()

    }

    private fun getCommentList() {
        intent.extras?.let { bundle ->
            viewModel.getCommentList((bundle.get(OBJECT_GALLERY_ITEM) as GalleryItem).id)
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setUpCommentList() {
        galleryDetail_rv.adapter = this.commentAdapter
    }

    private fun subscribeToGetCommentList() {
        this.viewModel.commentList.observe(this, Observer {
            this.commentAdapter.addData(it)
        })
    }

    companion object {
        const val OBJECT_GALLERY_ITEM = "OBJECT_GALLERY_ITEM"
    }

}