package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.CodeDataSource
import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse

class CodeDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi
) : CodeDataSource {
    //TODO update when the service is successful.
    override suspend fun verifyCode(eventCode: String): ResultType<EventCode, String> {
        return ResultType.Success(
            EventCode(
                1,
                1
            )
        )
    }
/*
    override suspend fun verifyCode(eventCode: String): ResultType<EventCode, String> {
        return when (val result = fiestonVirtualApi.validateCode(eventCode)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    EventCode(
                        idEvent = result.body.data?.user?.idUser,
                        userId = result.body.data?.event?.userInvitationCode
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
 */

}