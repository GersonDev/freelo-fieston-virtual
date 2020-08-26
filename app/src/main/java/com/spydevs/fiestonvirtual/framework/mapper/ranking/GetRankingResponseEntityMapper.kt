package com.spydevs.fiestonvirtual.framework.mapper.ranking

import com.spydevs.fiestonvirtual.data.entities.response.GetRankingResponseEntity
import com.spydevs.fiestonvirtual.domain.models.ranking.GetRankingResponse

fun GetRankingResponseEntity.toDomain() = GetRankingResponse(
    ranking = this.data.raking.map {
        it.toDomain()
    },
    userTotalScore = this.data.userTotalScore
)