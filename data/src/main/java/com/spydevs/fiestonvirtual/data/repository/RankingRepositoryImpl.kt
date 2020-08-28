package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.RankingDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingRequest
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingResponse
import com.spydevs.fiestonvirtual.domain.repository.RankingRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class RankingRepositoryImpl(
    private val rankingDataSource: RankingDataSource
) : RankingRepository {

    override suspend fun getRanking(
        getRankingRequest: GetRankingRequest
    ): ResultType<GetRankingResponse, ErrorResponse> {
        return rankingDataSource.getRanking(getRankingRequest)
    }

}