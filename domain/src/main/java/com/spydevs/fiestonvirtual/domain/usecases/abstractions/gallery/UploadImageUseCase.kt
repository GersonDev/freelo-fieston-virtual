package com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery

import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * Use case to upload a desire image to the server
 */
interface UploadImageUseCase {
    /**
     * Invoke the action of uploading the image to the server
     * @param [galleryImageRequest] this is an converted image to base64
     * @return the response model from the server
     */
    suspend operator fun invoke(galleryImageRequest: GalleryImageRequest): ResultType<GalleryImage, String>
}