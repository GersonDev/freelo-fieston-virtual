package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingRequest
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface RankingRepository {
    /**
     * This method get ranking and total score of the user.
     */
    suspend fun getRanking(
        getRankingRequest: GetRankingRequest
    ): ResultType<GetRankingResponse, ErrorResponse>
}