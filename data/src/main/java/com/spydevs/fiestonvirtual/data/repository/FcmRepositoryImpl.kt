package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.FcmDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenRequest
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenResponse
import com.spydevs.fiestonvirtual.domain.repository.FcmRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class FcmRepositoryImpl(
    private val fcmDataSource: FcmDataSource
) : FcmRepository {

    override suspend fun sendToken(
        sendTokenRequest: SendTokenRequest
    ): ResultType<SendTokenResponse, ErrorResponse> {
        return fcmDataSource.sendToken(sendTokenRequest)
    }

}