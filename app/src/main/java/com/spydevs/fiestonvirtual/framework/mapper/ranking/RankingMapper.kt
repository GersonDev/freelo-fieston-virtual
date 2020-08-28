package com.spydevs.fiestonvirtual.framework.mapper.ranking

import com.spydevs.fiestonvirtual.data.entities.response.GetRankingResponseEntity
import com.spydevs.fiestonvirtual.domain.models.ranking.Ranking

fun GetRankingResponseEntity.Data.Raking.toDomain() = Ranking(
    id = this.idUserRanking,
    userLogo = this.userRankingLogo,
    userName = this.userRankingName,
    position = this.userRankingPosition,
    totalScore = this.userRankingTotalScore
)