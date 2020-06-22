package com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery

import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.UploadImageUseCase

class UploadImageUseCaseImpl(private val galleryRepository: GalleryRepository): UploadImageUseCase {
    override suspend fun invoke(image: String): String {
        return galleryRepository.uploadImage(image)
    }
}