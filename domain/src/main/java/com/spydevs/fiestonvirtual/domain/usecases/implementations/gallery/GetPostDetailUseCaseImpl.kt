package com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetPostDetailUseCase

class GetPostDetailUseCaseImpl(
    private val galleryRepository: GalleryRepository
) : GetPostDetailUseCase {
    override suspend operator fun invoke(postId: Int): ResultType<List<GalleryItem>, ErrorResponse> {
        return galleryRepository.getGallery(
            GalleryRequest(
                idUser = null,
                idEvent = null,
                postStatus = null,
                idPost = postId
            )
        )
    }

}