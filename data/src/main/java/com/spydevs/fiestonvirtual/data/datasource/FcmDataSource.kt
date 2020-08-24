package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenRequest
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface FcmDataSource {

    /**
     * This method send the token to server.
     * @param sendTokenRequest is the object that content the token.
     */
    suspend fun sendToken(
        sendTokenRequest: SendTokenRequest
    ): ResultType<SendTokenResponse, ErrorResponse>

}