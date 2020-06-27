package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.GalleryImageResponseEntity
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object GalleryImageMapper: Mapper<GalleryImageResponseEntity?, GalleryImage>() {
    override fun convertFromInitial(i: GalleryImageResponseEntity?): GalleryImage {
        return GalleryImage(i?.imageUrl)
    }
}