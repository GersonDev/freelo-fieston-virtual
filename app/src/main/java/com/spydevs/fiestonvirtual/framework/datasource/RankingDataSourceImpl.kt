package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.RankingDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenResponse
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingRequest
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.ranking.toDomain

class RankingDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi
) : RankingDataSource {

    override suspend fun getRanking(
        getRankingRequest: GetRankingRequest
    ): ResultType<GetRankingResponse, ErrorResponse> {
        return when (val result = fiestonVirtualApi.getRanking(getRankingRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    result.body.toDomain()
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(
                    ErrorResponse(message = result.error.message ?: "")
                )
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(
                    ErrorResponse(message = result.error.message ?: "")
                )
            }
        }
    }

}