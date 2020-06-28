package com.spydevs.fiestonvirtual.ui.main.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem

class GalleryItemAdapter(
    private val onPhotoClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<GalleryItemViewHolder>() {

    private var photoList: List<GalleryItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        return GalleryItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_photo, parent, false)
        ) { position ->
            onPhotoClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    fun addData(photoList: List<GalleryItem>) {
        this.photoList = photoList
        notifyDataSetChanged()
    }

}