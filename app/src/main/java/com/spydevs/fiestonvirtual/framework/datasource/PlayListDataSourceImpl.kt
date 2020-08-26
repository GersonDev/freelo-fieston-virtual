package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.PlayListDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.GetPlaylistRequest
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import com.spydevs.fiestonvirtual.domain.models.playlist.SongRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.api.PlayListApi
import com.spydevs.fiestonvirtual.framework.mapper.implementations.convertToSong
import com.spydevs.fiestonvirtual.framework.mapper.implementations.convertToSongs

class PlayListDataSourceImpl(
    private val playListApi: PlayListApi
): PlayListDataSource {

    override suspend fun requestSong(songRequest: SongRequest): ResultType<Song, ErrorResponse> {
        return when (val result =
            playListApi.requestSong(songRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(result.body.convertToSong())
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
        }
    }

    override suspend fun getPlaylist(getPlaylistRequest: GetPlaylistRequest): ResultType<List<Song>, ErrorResponse> {
        return when (val result =
            playListApi.getPlaylist(getPlaylistRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(result.body.convertToSongs())
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
        }
    }

}