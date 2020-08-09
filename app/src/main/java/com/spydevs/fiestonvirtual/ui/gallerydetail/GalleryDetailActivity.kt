package com.spydevs.fiestonvirtual.ui.gallerydetail

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.ui.gallerydetail.adapter.CommentAdapter
import com.spydevs.fiestonvirtual.util.extensions.loadUrl
import com.spydevs.fiestonvirtual.util.extensions.setupLoadingAlertDialog
import com.spydevs.fiestonvirtual.util.extensions.show
import kotlinx.android.synthetic.main.content_gallery_detail.*
import kotlinx.android.synthetic.main.toolbar_simple.*
import org.koin.android.ext.android.inject

class GalleryDetailActivity : AppCompatActivity() {

    private val getCommentsLoadingDialog by lazy {
        setupLoadingAlertDialog()
    }

    private val addCommentLoadingDialog by lazy {
        setupLoadingAlertDialog()
    }

    private val makeLikeLoadingDialog by lazy {
        setupLoadingAlertDialog()
    }

    private val viewModel: GalleryDetailViewModel by inject()
    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    private var postId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)
        setUpIntentExtras()
        setUpViews()
        setUpLikeView()
        setUpViewListeners()
        subscribeToAnyError()
        subscribeToPostDetails()
        subscribeToCommentsResult()
        subscribeToMakeLike()
        viewModel.getComments(postId)
        viewModel.getPostDetail(postId)
    }

    private fun setUpLikeView() {
        galleryDetail_iv_favorite.setOnClickListener {
            viewModel.makeLike(postId)
        }
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
            when (it) {
                is GalleryDetailResult.GetComments.Success -> {
                    this.commentAdapter.addData(it.comments)
                }
                is GalleryDetailResult.GetComments.Loading -> {
                    this.getCommentsLoadingDialog.show(it.loading)
                }
                is GalleryDetailResult.AddComment.Success -> {
                    this.commentAdapter.addData(it.comment)
                    this.galleryDetail_et_comment.text?.clear()
                }
                is GalleryDetailResult.AddComment.Loading -> {
                    this.addCommentLoadingDialog.show(it.loading)
                }
            }
        })
    }

    private fun subscribeToPostDetails() {
        this.viewModel.postDetail.observe(this, Observer {
            when (it) {
                is GalleryDetailResult.GetGalleryDetail.Success -> {
                    showNameUser(
                        it.galleryDetailResponse.userImage,
                        it.galleryDetailResponse.userName
                    )
                    showLikes(
                        it.galleryDetailResponse.postLike,
                        it.galleryDetailResponse.postLikeCount
                    )

                    showTitlePost(
                        it.galleryDetailResponse.userName,
                        it.galleryDetailResponse.postTitle
                    )
                    when (it.galleryDetailResponse.postType) {
                        GalleryItem.TYPE_PHOTO -> {
                            galleryDetail_iv_detail.visibility = View.VISIBLE
                            galleryDetail_iv_detail.loadUrl(it.galleryDetailResponse.postFile)
                        }
                        GalleryItem.TYPE_VIDEO -> {
                            galleryDetail_vv_detail.visibility = View.VISIBLE
                            val vidUri: Uri = Uri.parse(it.galleryDetailResponse.postFile)
                            galleryDetail_vv_detail.setVideoURI(vidUri)
                            galleryDetail_vv_detail.start()

                            val vidControl = MediaController(this)
                            vidControl.setAnchorView(galleryDetail_vv_detail)
                            galleryDetail_vv_detail.setMediaController(vidControl)
                        }
                    }
                }
            }
        })
    }

    private fun showLikes(showLike: Boolean, countLike: Int) {
        if (showLike) {
            galleryDetail_iv_favorite.background =
                resources.getDrawable(R.drawable.ic_baseline_favorite_36, null)
            galleryDetail_iv_favorite.setOnClickListener(null)
        } else {
            galleryDetail_iv_favorite.background =
                resources.getDrawable(R.drawable.ic_baseline_favorite_border_36, null)
        }
        galleryDetail_tv_likes.text = countLike.toString()
    }

    private fun showNameUser(avatarLink: String, nameUser: String) {
        galleryDetail_iv_user.loadUrl(avatarLink)
        galleryDetail_tv_nameUser.text = nameUser
    }

    private fun showTitlePost(nameUser: String, title: String) {
        galleryDetail_tv_nameUser2.text = nameUser
        galleryDetail_tv_title.text = title
    }

    private fun subscribeToMakeLike() {
        this.viewModel.makeLike.observe(this, Observer {
            when (it) {
                is GalleryDetailResult.MakeLike.Success -> {
                    galleryDetail_tv_likes.text = it.likes.toString()
                    galleryDetail_iv_favorite.background =
                        resources.getDrawable(R.drawable.ic_baseline_favorite_36, null)
                    galleryDetail_iv_favorite.setOnClickListener(null)
                }
                is GalleryDetailResult.MakeLike.Loading -> {
                    this.makeLikeLoadingDialog.show(it.loading)
                }
            }
        })
    }

    companion object {
        const val OBJECT_GALLERY_ITEM = "OBJECT_GALLERY_ITEM"
    }

}