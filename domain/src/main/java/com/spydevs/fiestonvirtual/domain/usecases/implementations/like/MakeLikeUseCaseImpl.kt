package com.spydevs.fiestonvirtual.domain.usecases.implementations.like

import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeRequest
import com.spydevs.fiestonvirtual.domain.repository.LikeRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.like.MakeLikeUseCase

class MakeLikeUseCaseImpl(
    private val usersRepository: UsersRepository,
    private val likeRepository: LikeRepository
) : MakeLikeUseCase {

    override suspend fun invoke(idPost: Int) {
        likeRepository.makeLike(
            MakeLikeRequest(
                usersRepository.getLocalUser().id,
                idPost
            )
        )
    }

}