package com.spydevs.fiestonvirtual.ui.main.gallery.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.photo.Photo

class PhotoAdapter(
    private val onPhotoClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<PhotoViewHolder>() {

    private var photoList: MutableList<Photo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_photo, parent, false)
        ) { position ->
            onPhotoClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    fun addData(photoList: MutableList<Photo>) {
        this.photoList = photoList
        notifyDataSetChanged()
    }

}