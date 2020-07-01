package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.CodeDataSource
import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse

class CodeDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi
) : CodeDataSource {

    override suspend fun verifyCode(
        validateCodeRequest: ValidateCodeRequest
    ): ResultType<EventCode, String> {
        return when (val result = fiestonVirtualApi.validateCode(validateCodeRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    EventCode(
                        idEvent = result.body.data.user.idUser,
                        idUser = result.body.data.event.idEvent
                    )
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body.message)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(result.error.message ?: "")
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(result.error.message ?: "")
            }
        }
    }

}