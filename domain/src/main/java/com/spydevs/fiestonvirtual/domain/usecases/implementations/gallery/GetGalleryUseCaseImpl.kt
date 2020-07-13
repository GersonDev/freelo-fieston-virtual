package com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetGalleryUseCase

class GetGalleryUseCaseImpl(
    private val galleryRepository: GalleryRepository,
    private val usersRepository: UsersRepository
) : GetGalleryUseCase {
    //TODO update to property postStatus.
    override suspend operator fun invoke(): ResultType<List<GalleryItem>, ErrorResponse> {
        val user = usersRepository.getLocalUser()
        return galleryRepository.getGallery(
            GalleryRequest(
                idUser = user.id,
                idEvent = user.idEvent,
                postStatus = 1
            )
        )
    }

}