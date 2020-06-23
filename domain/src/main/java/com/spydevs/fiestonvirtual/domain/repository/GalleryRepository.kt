package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This repository is used to interact user images with server
 */
interface GalleryRepository {
    /**
     * @param [userId] this is the user id.
     * @param [galleryImageRequest] this is the request object to send the image to the server
     * @return [ResultType] The result type with the galley image from the server
     */
    suspend fun uploadImage(userId: Int, galleryImageRequest: GalleryImageRequest): ResultType<GalleryImage, String>
}