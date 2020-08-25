package com.spydevs.fiestonvirtual.data.entities.response

data class GetPlaylistResponseEntity(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val playlist: List<Playlist>
    ) {
        data class Playlist(
            val idPlaylist: Int,
            val playlistBand: String,
            val playlistRegistrationDate: String,
            val playlistSong: String,
            val playlistStatus: Boolean
        )
    }
}