package com.spydevs.fiestonvirtual.domain.models.user

data class User(
    var id: Int = 0,
    var name: String = "",
    var lastName: String = "",
    var totalScore: Int = 0,
    var ranking: Int = 0
)