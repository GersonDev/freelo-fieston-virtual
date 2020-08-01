package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.GalleryResponseEntity
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object GalleryItemListMapper : Mapper<List<GalleryResponseEntity.Post>, List<GalleryItem>>() {

    override fun convertFromInitial(i: List<GalleryResponseEntity.Post>): List<GalleryItem> {
        return i.map {
            GalleryItem(
                it.idPost,
                it.postType,
                it.postFile,
                it.postStatus,
                it.preview
            )
        }
    }

}