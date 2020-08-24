package com.spydevs.fiestonvirtual.domain.usecases.implementations.playlist

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import com.spydevs.fiestonvirtual.domain.models.playlist.SongRequest
import com.spydevs.fiestonvirtual.domain.repository.PlaylistRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.playlist.RequestSongUseCase

class RequestSongUseCaseImpl(
    private val playlistRepository: PlaylistRepository,
    private val usersRepository: UsersRepository
) : RequestSongUseCase {
    override suspend fun invoke(idPlaylist: Int): ResultType<Song, ErrorResponse> {
        val songRequest = SongRequest(usersRepository.getLocalUser().id, idPlaylist)
        return playlistRepository.requestSong(songRequest)
    }
}