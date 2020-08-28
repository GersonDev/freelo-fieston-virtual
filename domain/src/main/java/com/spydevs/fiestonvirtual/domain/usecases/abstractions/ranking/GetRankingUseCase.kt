package com.spydevs.fiestonvirtual.domain.usecases.abstractions.ranking

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case is used to get ranking and total score of the user.
 */
interface GetRankingUseCase {

    suspend operator fun invoke(): ResultType<GetRankingResponse, ErrorResponse>

}