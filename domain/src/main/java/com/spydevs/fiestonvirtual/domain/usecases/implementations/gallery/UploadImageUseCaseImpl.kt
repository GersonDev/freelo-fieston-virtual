package com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery

import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.UploadImageUseCase

class UploadImageUseCaseImpl(private val galleryRepository: GalleryRepository): UploadImageUseCase {
    override suspend fun invoke(
        galleryImageRequest: GalleryImageRequest
    ): ResultType<GalleryImage, String> {

        //TODO USE SHARED PREFERENCES FOR GETTING THE USER ID
        return galleryRepository.uploadImage(1, galleryImageRequest)
    }
}