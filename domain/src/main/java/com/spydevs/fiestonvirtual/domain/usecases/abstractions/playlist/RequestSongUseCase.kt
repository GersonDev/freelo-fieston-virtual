package com.spydevs.fiestonvirtual.domain.usecases.abstractions.playlist

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import com.spydevs.fiestonvirtual.domain.models.playlist.SongRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case is used for requesting a song.
 */
interface RequestSongUseCase {

    suspend operator fun invoke(idPlaylist: Int): ResultType<Song, ErrorResponse>
}