package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.resource.Resource

interface GalleryDataSource {
    suspend fun uploadImage(image: String, galleryImageRequest: GalleryImageRequest): Resource<GalleryImage>
}