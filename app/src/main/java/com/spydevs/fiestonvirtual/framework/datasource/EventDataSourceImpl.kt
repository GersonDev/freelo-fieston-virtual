package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.EventDataSource
import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.models.welcome.WelcomeRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.implementations.WelcomeMapper

class EventDataSourceImpl(private val fiestonVirtualApi: FiestonVirtualApi) : EventDataSource {
    override suspend fun getWelcome(welcomeRequest: WelcomeRequest): ResultType<Welcome, String> {

        return when (val result = fiestonVirtualApi.getEventWelcome(welcomeRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    WelcomeMapper.convertFromInitial(result.body)
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
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