package com.spydevs.fiestonvirtual.domain.usecases.abstractions.code

import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case verify the event code.
 */
interface VerifyEventCodeUseCase {

    /**
     * @param [eventCode] this is the event code to enter the application.
     * @return all user data.
     */
    suspend operator fun invoke(eventCode: String): ResultType<EventCode, String>
}