package com.spydevs.fiestonvirtual.ui.main.gallery.photo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.util.extensions.loadUrl
import kotlinx.android.synthetic.main.layout_item_photo.view.*

class GalleryItemViewHolder(
    private val view: View,
    private val onPhotoClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    init {
        this.view.photoItemLayout_cv.setOnClickListener {
            onPhotoClickListener(adapterPosition)
        }
    }

    fun bind(photo: GalleryItem) {
        photo.file.let {
            this.view.photoItemLayout_iv.loadUrl(it)
        }
    }
}
