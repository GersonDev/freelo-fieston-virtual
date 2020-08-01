package com.spydevs.fiestonvirtual.ui.main.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem

class GalleryItemAdapter(
    private val onPhotoClickListener: (galleryItem: GalleryItem) -> Unit,
    private val onVideoClickListener: (galleryItem: GalleryItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var photoList: List<GalleryItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == GalleryItem.TYPE_PHOTO) {
            GalleryItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_photo, parent, false)
            ) { position ->
                onPhotoClickListener(photoList[position])
            }
        } else {
            GalleryItemVideoViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_video, parent, false)
            ) { position ->
                onVideoClickListener(photoList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return photoList[position].type
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun addData(photoList: List<GalleryItem>) {
        this.photoList = photoList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            GalleryItem.TYPE_PHOTO -> {
                (holder as GalleryItemViewHolder).bind(photoList[position])
            }
            GalleryItem.TYPE_VIDEO -> {
                (holder as GalleryItemVideoViewHolder).bind(photoList[position])
            }
        }
    }

}