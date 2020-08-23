package com.spydevs.fiestonvirtual.domain.usecases.abstractions.notifications

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case send token to server.
 */
interface SendTokenFirebaseUseCase {

    suspend operator fun invoke(
        token: String
    ): ResultType<Boolean, ErrorResponse>

}