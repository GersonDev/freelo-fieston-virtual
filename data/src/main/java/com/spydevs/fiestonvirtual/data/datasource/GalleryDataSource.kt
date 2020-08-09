package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.models.gallery.GetGalleryDetailRequest
import com.spydevs.fiestonvirtual.domain.models.gallery.GetGalleryDetailResponse
import com.spydevs.fiestonvirtual.domain.models.photo.Photo
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import okhttp3.MultipartBody

interface GalleryDataSource {
    suspend fun uploadImage(
        file: MultipartBody.Part, idUser: Int, eventId: Int, postType: Int
    ): ResultType<Photo, String>

    /**
     * @param [galleryRequest] this object is necessary for return the [ResultType].
     * @return [ResultType] this contains the images and videos from the gallery.
     */
    suspend fun getGallery(
        galleryRequest: GalleryRequest
    ): ResultType<List<GalleryItem>, ErrorResponse>


    suspend fun getGalleryDetail(
        getGalleryDetailRequest: GetGalleryDetailRequest
    ): ResultType<GetGalleryDetailResponse, ErrorResponse>
}