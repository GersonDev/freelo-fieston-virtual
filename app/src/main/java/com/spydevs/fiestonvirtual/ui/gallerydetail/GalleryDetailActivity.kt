package com.spydevs.fiestonvirtual.ui.gallerydetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.ui.gallerydetail.adapter.CommentAdapter
import com.spydevs.fiestonvirtual.util.extensions.loadUrl
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import com.spydevs.fiestonvirtual.util.extensions.show
import kotlinx.android.synthetic.main.activity_camera.toolbar
import kotlinx.android.synthetic.main.content_gallery_detail.*
import org.koin.android.ext.android.inject

class GalleryDetailActivity : AppCompatActivity() {

    private val getCommentsLoadingDialog by lazy {
        setupLoadingAlertDialog()
    }

    private val addCommentLoadingDialog by lazy {
        setupLoadingAlertDialog()
    }

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
        subscribeToPostDetails()
        subscribeToCommentsResult()
        viewModel.getComments(postId)
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
        galleryDetail_ib_send.setOnClickListener {
            viewModel.addComment(postId, galleryDetail_et_comment.text.toString())
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

    private fun subscribeToCommentsResult() {
        this.viewModel.commentResult.observe(this, Observer {
            when(it) {
                is CommentsResult.GetComments.Success -> {
                    this.commentAdapter.addData(it.comments)
                }
                is CommentsResult.GetComments.Loading -> {
                    this.getCommentsLoadingDialog.show(it.loading)
                }
                is CommentsResult.AddComment.Success -> {
                    this.commentAdapter.addData(it.comment)
                }
                is CommentsResult.AddComment.Loading -> {
                    this.addCommentLoadingDialog.show(it.loading)
                }
            }
        })
    }

    private fun subscribeToPostDetails() {
        this.viewModel.postDetail.observe(this, Observer {
            galleryDetail_iv_detail.loadUrl(it.galleryItemList[0].file)
        })
    }

    companion object {
        const val OBJECT_GALLERY_ITEM = "OBJECT_GALLERY_ITEM"
    }

}