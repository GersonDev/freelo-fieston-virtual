package com.spydevs.fiestonvirtual.ui.gallerydetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.comment.Comment
import com.spydevs.fiestonvirtual.ui.gallerydetail.adapter.CommentAdapter
import kotlinx.android.synthetic.main.activity_camera.toolbar
import kotlinx.android.synthetic.main.content_gallery_detail.*

class GalleryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)
        setUpToolbar()
        //TODO add ViewModel.
        galleryDetail_rv.adapter = CommentAdapter().apply {
            addData(
                mutableListOf(
                    Comment(
                        1,
                        "chevre",
                        "https://pngimg.com/uploads/face/face_PNG11760.png"
                    ),
                    Comment(
                        1,
                        "bacan",
                        "https://pngimg.com/uploads/face/face_PNG11760.png"
                    ),
                    Comment(
                        1,
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                        "https://pngimg.com/uploads/face/face_PNG11760.png"
                    ),
                    Comment(
                        1,
                        "exito",
                        "https://pngimg.com/uploads/face/face_PNG11760.png"
                    ),
                    Comment(
                        1,
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                        "https://pngimg.com/uploads/face/face_PNG11760.png"
                    ),
                    Comment(
                        1,
                        "eliminar",
                        "https://pngimg.com/uploads/face/face_PNG11760.png"
                    ),
                    Comment(
                        1,
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                        "https://pngimg.com/uploads/face/face_PNG11760.png"
                    )
                )

            )
        }

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

}