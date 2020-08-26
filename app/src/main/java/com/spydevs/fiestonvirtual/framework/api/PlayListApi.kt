package com.spydevs.fiestonvirtual.framework.api

import com.spydevs.fiestonvirtual.data.entities.response.GetPlaylistResponseEntity
import com.spydevs.fiestonvirtual.data.entities.response.RequestSongResponseEntity
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.GetPlaylistRequest
import com.spydevs.fiestonvirtual.domain.models.playlist.SongRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface PlayListApi {

    @POST("pedir_cancion.php")
    suspend fun requestSong(
        @Body songRequest: SongRequest
    ): NetworkResponse<RequestSongResponseEntity, ErrorResponse>

    @POST("lista_playlist.php")
    suspend fun getPlaylist(
        @Body getPlaylistRequest: GetPlaylistRequest
    ): NetworkResponse<GetPlaylistResponseEntity, ErrorResponse>

}