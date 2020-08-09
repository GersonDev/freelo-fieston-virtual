package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.*
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.models.photo.Photo
import okhttp3.MultipartBody

/**
 * This repository is used to interact user images with server
 */
interface GalleryRepository {
    /**
     * @param [userId] this is the user id.
     * @param [galleryImageRequest] this is the request object to send the image to the server
     * @return [ResultType] The result type with the galley image from the server
     */
    suspend fun uploadImage(
        file: MultipartBody.Part, idUser: Int, eventId: Int, postType: Int
    ): ResultType<Photo, String>

    /**
     * @param [galleryRequest] This object is necessary for return the [ResultType].
     * @return [ResultType] This contains the images and videos from the gallery.
     */
    suspend fun getGallery(
        galleryRequest: GalleryRequest
    ): ResultType<List<GalleryItem>, ErrorResponse>

    suspend fun getGalleryDetail(
        getGalleryDetailRequest: GetGalleryDetailRequest
    ): ResultType<GetGalleryDetailResponse, ErrorResponse>

}