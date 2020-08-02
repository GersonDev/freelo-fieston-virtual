package com.spydevs.fiestonvirtual.ui.main.gallery.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.util.extensions.loadUrl
import kotlinx.android.synthetic.main.layout_item_video.view.*

class GalleryItemVideoViewHolder(
    private val view: View,
    private val onVideoClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    init {
        this.view.videoItemLayout_cv.setOnClickListener {
            onVideoClickListener(adapterPosition)
        }
    }

    fun bind(photo: GalleryItem) {
        photo.preview.let {
            it?.let {
                this.view.videoItemLayout_iv.loadUrl(it)
            }
        }
    }
}
