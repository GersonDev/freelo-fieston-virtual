package com.spydevs.fiestonvirtual.domain.models.notifications

data class SendTokenRequest(
    val idUser: Int,
    val token: String
)