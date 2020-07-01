package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This data source is used for get data the code of the event.
 */
interface CodeDataSource {
    /**
     * @param [validateCodeRequest] This content the event code to enter the application.
     * @return the [EventCode].
     */
    suspend fun verifyCode(
        validateCodeRequest: ValidateCodeRequest
    ): ResultType<EventCode, ErrorResponse>

}