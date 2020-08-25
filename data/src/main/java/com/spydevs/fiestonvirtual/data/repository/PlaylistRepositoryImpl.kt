package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.PlayListDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.GetPlaylistRequest
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import com.spydevs.fiestonvirtual.domain.models.playlist.SongRequest
import com.spydevs.fiestonvirtual.domain.repository.PlaylistRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class PlaylistRepositoryImpl(
    private val playListDataSource: PlayListDataSource
): PlaylistRepository {
    override suspend fun requestSong(songRequest: SongRequest): ResultType<Song, ErrorResponse> {
        return playListDataSource.requestSong(songRequest)
    }

    override suspend fun getRemotePlaylist(getPlaylistRequest: GetPlaylistRequest): ResultType<List<Song>, ErrorResponse> {
        return playListDataSource.getPlaylist(getPlaylistRequest)
    }
}