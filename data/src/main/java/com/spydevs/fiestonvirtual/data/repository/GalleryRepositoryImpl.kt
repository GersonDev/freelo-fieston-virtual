package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class GalleryRepositoryImpl(
    private val galleryDataSource: GalleryDataSource
) : GalleryRepository {
    override suspend fun uploadImage(
        userId: Int,
        galleryImageRequest: GalleryImageRequest
    ): ResultType<GalleryImage, String> {
        return galleryDataSource.uploadImage(userId, galleryImageRequest)
    }

    override suspend fun getGallery(galleryRequest: GalleryRequest): ResultType<List<GalleryItem>, ErrorResponse> {
        return galleryDataSource.getGallery(galleryRequest)
    }

}