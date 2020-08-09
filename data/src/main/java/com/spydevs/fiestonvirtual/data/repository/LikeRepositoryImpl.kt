package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.LikeDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeRequest
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeResponse
import com.spydevs.fiestonvirtual.domain.repository.LikeRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class LikeRepositoryImpl(
    private val likeDataSource: LikeDataSource
) : LikeRepository {

    override suspend fun makeLike(
        makeLikeRequest: MakeLikeRequest
    ): ResultType<MakeLikeResponse, ErrorResponse> {
        return likeDataSource.makeLike(makeLikeRequest)
    }

}