package com.spydevs.fiestonvirtual.domain.usecases.implementations.playlist

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.GetPlaylistRequest
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import com.spydevs.fiestonvirtual.domain.repository.PlaylistRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.playlist.GetPlayListUseCase

class GetPlaylistUseCaseImpl(
    private val playlistRepository: PlaylistRepository,
    private val usersRepository: UsersRepository
): GetPlayListUseCase {
    override suspend fun invoke(): ResultType<List<Song>, ErrorResponse> {
        //TODO for now we are going to request by an empty string
        val getPlaylistRequest = GetPlaylistRequest(usersRepository.getLocalUser().idEvent, "")
        return playlistRepository.getRemotePlaylist(getPlaylistRequest)
    }
}