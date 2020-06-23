package com.spydevs.fiestonvirtual.domain.usecases.abstractions.code

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse

/**
 * This use case verify the event code.
 */
interface VerifyEventCodeUseCase {

    /**
     * @param [eventCode] this is the event code to enter the application.
     * @return all user data.
     */
    suspend fun invoke(eventCode: String): CodeResponse
}