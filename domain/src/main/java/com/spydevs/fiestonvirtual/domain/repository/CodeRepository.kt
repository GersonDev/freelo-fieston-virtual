package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This repository is used to verify the code of the event.
 */
interface CodeRepository {

    /**
     * @param [validateCodeRequest] this content the event code to enter the application.
     * @return the data of the registered user.
     */
    suspend fun verifyCode(
        validateCodeRequest: ValidateCodeRequest
    ): ResultType<EventCode, ErrorResponse>

}