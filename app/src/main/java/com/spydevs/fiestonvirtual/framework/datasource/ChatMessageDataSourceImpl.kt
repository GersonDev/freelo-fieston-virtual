package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.ChatMessageDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.MessageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.implementations.toMessages

class ChatMessageDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi
): ChatMessageDataSource {
    override suspend fun getRemoteMessages(messageRequest: MessageRequest): ResultType<List<ChatMessage>, ErrorResponse> {
        return when (val result = fiestonVirtualApi.getMessages(messageRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(result.body.toMessages())
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
        }
    }
}