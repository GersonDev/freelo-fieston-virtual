package com.spydevs.fiestonvirtual.domain.models.playlist

data class GetPlaylistRequest(
    val idEvent: Int,
    val search: String
)