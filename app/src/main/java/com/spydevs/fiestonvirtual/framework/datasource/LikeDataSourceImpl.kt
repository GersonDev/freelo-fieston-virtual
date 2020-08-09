package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.LikeDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeRequest
import com.spydevs.fiestonvirtual.domain.models.like.MakeLikeResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse

class LikeDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi
) : LikeDataSource {

    override suspend fun makeLike(
        makeLikeRequest: MakeLikeRequest
    ): ResultType<MakeLikeResponse, ErrorResponse> {
        return when (val result =
            fiestonVirtualApi.makeLike(makeLikeRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    MakeLikeResponse(
                        result.body.data.userLikes
                    )
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
        }
    }
}