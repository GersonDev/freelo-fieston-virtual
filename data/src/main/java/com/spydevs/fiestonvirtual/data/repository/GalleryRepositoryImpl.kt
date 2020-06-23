package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class GalleryRepositoryImpl(private val galleryDataSource: GalleryDataSource): GalleryRepository {
    override suspend fun uploadImage(
        userId: Int,
        galleryImageRequest: GalleryImageRequest
    ): ResultType<GalleryImage, String> {
        return galleryDataSource.uploadImage(userId, galleryImageRequest)
    }
}