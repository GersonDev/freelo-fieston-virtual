package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface GalleryRepository {
    suspend fun uploadImage(userId: Int, galleryImageRequest: GalleryImageRequest): ResultType<GalleryImage, String>
}