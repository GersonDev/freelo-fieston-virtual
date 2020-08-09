package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeRequest
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface LikeRepository {
    suspend fun makeLike(
        makeLikeRequest: MakeLikeRequest
    ): ResultType<MakeLikeResponse, ErrorResponse>
}