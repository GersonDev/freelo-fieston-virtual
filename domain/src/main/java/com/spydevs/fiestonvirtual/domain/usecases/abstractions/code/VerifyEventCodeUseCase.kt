package com.spydevs.fiestonvirtual.domain.usecases.abstractions.code

import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case verify the event code.
 */
interface VerifyEventCodeUseCase {

    /**
     * @param [validateCodeRequest] this content the event code to enter the application.
     * @return all user data.
     */
    suspend operator fun invoke(
        validateCodeRequest: ValidateCodeRequest
    ): ResultType<EventCode, ErrorResponse>

}