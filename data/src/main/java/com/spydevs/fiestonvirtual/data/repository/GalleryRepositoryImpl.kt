package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository

class GalleryRepositoryImpl(private val galleryDataSource: GalleryDataSource): GalleryRepository {
    override suspend fun uploadImage(base64Image: String): String {
        return galleryDataSource.uploadImage(base64Image)
    }
}