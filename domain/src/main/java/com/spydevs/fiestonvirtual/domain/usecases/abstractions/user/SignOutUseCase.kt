package com.spydevs.fiestonvirtual.domain.usecases.abstractions.user

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case is used to sign out.
 */
interface SignOutUseCase {

    suspend operator fun invoke(): ResultType<Boolean, ErrorResponse>

}