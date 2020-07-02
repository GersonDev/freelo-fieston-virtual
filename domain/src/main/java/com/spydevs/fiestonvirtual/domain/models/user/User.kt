package com.spydevs.fiestonvirtual.domain.models.user

data class User(
    var id: Int,
    var name: String,
    var lastName: String,
    var totalScore: Int,
    var ranking: Int,
    var idEvent: Int
)