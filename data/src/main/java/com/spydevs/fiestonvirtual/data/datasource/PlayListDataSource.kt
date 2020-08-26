package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.GetPlaylistRequest
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import com.spydevs.fiestonvirtual.domain.models.playlist.SongRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This data source is used for handling play list operations.
 */
interface PlayListDataSource {

    /**
     * @param [songRequest] This content the event code to enter the application.
     * @return the [Song].
     */
    suspend fun requestSong(
        songRequest: SongRequest
    ): ResultType<Song, ErrorResponse>

    suspend fun getPlaylist(
        getPlaylistRequest: GetPlaylistRequest
    ): ResultType<List<Song>, ErrorResponse>
}