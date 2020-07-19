package com.spydevs.fiestonvirtual.ui.gallerydetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.ui.gallerydetail.adapter.CommentAdapter
import com.spydevs.fiestonvirtual.ui.main.gallery.GalleryViewModel
import com.spydevs.fiestonvirtual.util.extensions.loadUrl
import kotlinx.android.synthetic.main.activity_camera.toolbar
import kotlinx.android.synthetic.main.content_gallery_detail.*
import org.koin.android.ext.android.inject

class GalleryDetailActivity : AppCompatActivity() {

    private val viewModel: GalleryDetailViewModel by inject()
    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    private var postId  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)
        setUpIntentExtras()
        setUpViews()
        setUpViewListeners()
        subscribeToAnyError()
        subscribeToGetCommentList()
        subscribeToPostDetails()
        viewModel.getCommentList(postId)
        viewModel.getPostDetail(postId)
    }

    private fun setUpIntentExtras() {
        intent.extras?.let { bundle ->
            postId = (bundle.get(OBJECT_GALLERY_ITEM) as GalleryItem).id
        }
    }

    private fun setUpViewListeners() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setUpViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        galleryDetail_rv.adapter = this.commentAdapter
    }

    private fun subscribeToAnyError() {
        this.viewModel.error.observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun subscribeToPostDetails() {
        this.viewModel.postDetail.observe(this, Observer {
            galleryDetail_iv_detail.loadUrl(it.galleryItemList[0].file)
        })
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