package com.spydevs.fiestonvirtual.ui.playlist

import com.spydevs.fiestonvirtual.domain.models.playlist.Song

sealed class PlaylistResult {

    sealed class RequestSong : PlaylistResult() {
        data class Success(val song: Song) : RequestSong()
        data class Loading(var loading: Boolean) : RequestSong()
        data class Error(var error: String) : RequestSong()
    }

    sealed class GetPlaylist : PlaylistResult() {
        data class Success(val songs: List<Song>) : GetPlaylist()
        data class Loading(var loading: Boolean) : GetPlaylist()
        data class Error(var error: String) : GetPlaylist()
    }
}