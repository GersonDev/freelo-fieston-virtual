package com.spydevs.fiestonvirtual.domain.usecases.abstractions.playlist

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.playlist.Song
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case is used for getting the playlist.
 */
interface GetPlayListUseCase {

    suspend operator fun invoke(): ResultType<List<Song>, ErrorResponse>
}