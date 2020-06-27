package com.spydevs.fiestonvirtual.framework.mapper.frominitial

import com.spydevs.fiestonvirtual.data.entities.response.GalleryResponseEntity
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem

/**
 * TODO refactor object with a abstraction.
 */
object GalleryItemListMapper {

    fun convertFromInitial(i: List<GalleryResponseEntity.Post>): List<GalleryItem> {
        return i.map {
            GalleryItem(
                it.idPost,
                it.postType,
                it.postFile,
                it.postStatus
            )
        }.toList()
    }

}