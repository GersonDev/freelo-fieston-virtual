package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.models.photo.Photo
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import okhttp3.MultipartBody

class GalleryRepositoryImpl(
    private val galleryDataSource: GalleryDataSource
) : GalleryRepository {
    override suspend fun uploadImage(
        file: MultipartBody.Part, idUser: Int, eventId: Int, postType: Int
    ): ResultType<Photo, String> {
        return galleryDataSource.uploadImage(file, idUser, eventId, postType)
    }

    override suspend fun getGallery(galleryRequest: GalleryRequest): ResultType<List<GalleryItem>, ErrorResponse> {
        return galleryDataSource.getGallery(galleryRequest)
    }

}