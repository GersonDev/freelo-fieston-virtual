package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeRequest
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface LikeDataSource {
    suspend fun makeLike(
        makeLikeRequest: MakeLikeRequest
    ): ResultType<MakeLikeResponse, ErrorResponse>
}