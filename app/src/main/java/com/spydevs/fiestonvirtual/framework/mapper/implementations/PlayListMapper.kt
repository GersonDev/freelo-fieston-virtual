package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.GetPlaylistResponseEntity
import com.spydevs.fiestonvirtual.data.entities.response.RequestSongResponseEntity
import com.spydevs.fiestonvirtual.domain.models.playlist.Song

fun RequestSongResponseEntity.convertToSong(): Song {
    //TODO populate this data once the web service is ready
    return Song(
        id = this.data.idPlaylist,
        title = "",
        band = "",
        requested = this.data.playlistStatus
    )
}

fun GetPlaylistResponseEntity.convertToSongs(): List<Song> {
    //TODO populate this data once the web service is ready
    return this.data.playlist.map {
        Song(
            id = it.idPlaylist,
            title = it.playlistSong,
            band = it.playlistBand,
            requested = it.playlistStatus
        )
    }

}