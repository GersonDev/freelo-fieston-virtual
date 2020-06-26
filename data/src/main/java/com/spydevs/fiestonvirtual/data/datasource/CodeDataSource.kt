package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This data source is used for get data the code of the event.
 */
interface CodeDataSource {
    /**
     * @param [eventCode] this is the event code to enter the application.
     * @return the [EventCode].
     */
    suspend fun verifyCode(eventCode: String): ResultType<EventCode, String>

}