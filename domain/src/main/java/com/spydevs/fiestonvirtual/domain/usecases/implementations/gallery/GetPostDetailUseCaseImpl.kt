package com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GetGalleryDetailRequest
import com.spydevs.fiestonvirtual.domain.models.gallery.GetGalleryDetailResponse
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetPostDetailUseCase

class GetPostDetailUseCaseImpl(
    private val galleryRepository: GalleryRepository,
    private val usersRepository: UsersRepository
) : GetPostDetailUseCase {
    override suspend operator fun invoke(
        postId: Int
    ): ResultType<GetGalleryDetailResponse, ErrorResponse> {
        return galleryRepository.getGalleryDetail(
            GetGalleryDetailRequest(
                idUserSesion = usersRepository.getLocalUser().id,
                idPost = postId
            )
        )
    }

}