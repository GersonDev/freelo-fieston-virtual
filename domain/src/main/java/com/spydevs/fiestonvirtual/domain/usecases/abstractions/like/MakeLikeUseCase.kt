package com.spydevs.fiestonvirtual.domain.usecases.abstractions.like

interface MakeLikeUseCase {
    suspend operator fun invoke(idPost: Int)
}