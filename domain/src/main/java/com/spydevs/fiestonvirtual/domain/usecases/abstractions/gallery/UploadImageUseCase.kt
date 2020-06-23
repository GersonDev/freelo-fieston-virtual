package com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery

import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface UploadImageUseCase {
    suspend operator fun invoke(galleryImageRequest: GalleryImageRequest): ResultType<GalleryImage, String>
}