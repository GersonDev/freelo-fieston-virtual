package com.spydevs.fiestonvirtual.framework.mapper.frominitial

import com.spydevs.fiestonvirtual.data.models.GalleryImageResponse
import com.spydevs.fiestonvirtual.domain.models.GalleryImage
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object GalleryImageMapper: Mapper<GalleryImageResponse, GalleryImage>() {
    override fun convertFromInitial(i: GalleryImageResponse?): GalleryImage {
        return GalleryImage(i?.imageUrl)
    }
}