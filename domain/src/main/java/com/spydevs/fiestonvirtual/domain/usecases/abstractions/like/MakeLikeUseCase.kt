package com.spydevs.fiestonvirtual.domain.usecases.abstractions.like

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface MakeLikeUseCase {
    suspend operator fun invoke(
        idPost: Int
    ): ResultType<MakeLikeResponse, ErrorResponse>
}