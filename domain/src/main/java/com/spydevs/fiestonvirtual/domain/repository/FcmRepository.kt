package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenRequest
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface FcmRepository {

    /**
     * This method send the token to server.
     * @param sendTokenRequest is the object that content the token.
     */
    suspend fun sendToken(
        sendTokenRequest: SendTokenRequest
    ): ResultType<SendTokenResponse, ErrorResponse>

}