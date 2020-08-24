package com.spydevs.fiestonvirtual.data.entities.response

data class RequestSongResponseEntity(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val idPlaylist: Int,
        val playlistStatus: Boolean
    )
}