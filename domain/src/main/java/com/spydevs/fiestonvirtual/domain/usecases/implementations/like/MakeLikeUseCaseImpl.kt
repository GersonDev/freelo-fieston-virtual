package com.spydevs.fiestonvirtual.domain.usecases.implementations.like

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeRequest
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeResponse
import com.spydevs.fiestonvirtual.domain.repository.LikeRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.like.MakeLikeUseCase

class MakeLikeUseCaseImpl(
    private val usersRepository: UsersRepository,
    private val likeRepository: LikeRepository
) : MakeLikeUseCase {

    override suspend fun invoke(
        idPost: Int
    ): ResultType<MakeLikeResponse, ErrorResponse> {
        return likeRepository.makeLike(
            MakeLikeRequest(
                usersRepository.getLocalUser().id,
                idPost
            )
        )
    }

}