package com.spydevs.fiestonvirtual.domain.usecases.abstractions.user

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case verify if exists a user logged.
 */
interface VerificationSessionUseCase {

    suspend operator fun invoke(): ResultType<Boolean, ErrorResponse>

}