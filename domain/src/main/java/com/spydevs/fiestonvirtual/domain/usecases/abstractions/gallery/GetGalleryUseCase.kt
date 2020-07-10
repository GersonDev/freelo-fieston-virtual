package com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case is used to get a gallery(images and videos).
 */
interface GetGalleryUseCase {

    /**
     * @return [ResultType] This contains the images and videos from the gallery.
     */
    suspend operator fun invoke(): ResultType<List<GalleryItem>, ErrorResponse>

}