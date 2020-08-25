package com.spydevs.fiestonvirtual.domain.models.playlist

data class Song(
    val id: Int,
    val title: String,
    val band: String,
    var requested: Boolean
)
