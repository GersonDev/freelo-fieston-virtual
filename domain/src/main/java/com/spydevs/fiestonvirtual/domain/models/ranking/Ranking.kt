package com.spydevs.fiestonvirtual.domain.models.ranking

data class Ranking(
    val id: Int,
    val userName: String,
    val totalScore: Int,
    val position: Int,
    val userLogo: String
)