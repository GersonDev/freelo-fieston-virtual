package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.GetPlaylistRequest
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import com.spydevs.fiestonvirtual.domain.models.playlist.SongRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This repository is used to interact play list with server
 */
interface PlaylistRepository {

    /**
     * @param [songRequest] This content the event code to enter the application.
     * @return the [Song].
     */
    suspend fun requestSong(
        songRequest: SongRequest
    ): ResultType<Song, ErrorResponse>

    suspend fun getRemotePlaylist(
        getPlaylistRequest: GetPlaylistRequest
    ): ResultType<List<Song>, ErrorResponse>

}