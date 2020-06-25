package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This repository is used to verify the code of the event.
 */
interface CodeRepository {

    /**
     * @param [eventCode] this is the event code to enter the application.
     * @return the data of the registered user.
     */
    suspend fun verifyCode(eventCode: String): ResultType<EventCode, String>

}