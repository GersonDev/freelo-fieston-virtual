package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.FcmDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenRequest
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenResponse
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse

class FcmDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi
) : FcmDataSource {

    override suspend fun sendToken(
        sendTokenRequest: SendTokenRequest
    ): ResultType<SendTokenResponse, ErrorResponse> {
        return when (val result = fiestonVirtualApi.sendToken(sendTokenRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    SendTokenResponse(result.body.data.rpta)
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(
                    ErrorResponse(message = result.error.message ?: "")
                )
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(
                    ErrorResponse(message = result.error.message ?: "")
                )
            }
        }
    }

}