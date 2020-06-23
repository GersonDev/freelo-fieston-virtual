package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface GalleryDataSource {
    suspend fun uploadImage(userId: Int, galleryImageRequest: GalleryImageRequest): ResultType<GalleryImage, String>
}