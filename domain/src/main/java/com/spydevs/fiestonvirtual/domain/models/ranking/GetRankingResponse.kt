package com.spydevs.fiestonvirtual.domain.models.ranking

data class GetRankingResponse(
    val ranking: List<Ranking>,
    val userTotalScore: String
)