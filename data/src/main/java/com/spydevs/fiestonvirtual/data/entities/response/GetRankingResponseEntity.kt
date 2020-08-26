package com.spydevs.fiestonvirtual.data.entities.response

data class GetRankingResponseEntity(
    val data: Data,
    val message: String
) {
    data class Data(
        val raking: List<Raking>,
        val userTotalScore: String
    ) {
        data class Raking(
            val idUserRanking: Int,
            val userRankingLogo: String,
            val userRankingName: String,
            val userRankingPosition: Int,
            val userRankingTotalScore: Int
        )
    }
}