package com.spydevs.fiestonvirtual.ui.ranking

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.ranking.Ranking

sealed class RankingResult {

    sealed class GetRanking : RankingResult() {
        data class Loading(val show: Boolean) : GetRanking()
        data class Success(val totalScore: String, val ranking: List<Ranking>) : GetRanking()
        data class Error(val error: ErrorResponse) : GetRanking()
    }

}