package com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetGalleryUseCase

class GetGalleryUseCaseImpl(
    private val galleryRepository: GalleryRepository
) : GetGalleryUseCase {

    override suspend operator fun invoke(
        galleryRequest: GalleryRequest
    ): ResultType<List<GalleryItem>, ErrorResponse> {
        return galleryRepository.getGallery(galleryRequest)
    }

}